package com.jxpress.http;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class JServer {

    private JMiddleWare middleWare;
    private ServerSocket myServerSocket;
    private Thread myThread;
    private Set<Socket> openConnections = new HashSet<>();
    public static final int SOCKET_READ_TIMEOUT = 5000;
    private AsyncRunner asyncRunner = new DefaultAsyncRunner();

    public interface AsyncRunner {
        void exec(Runnable code);
    }

    public static class DefaultAsyncRunner implements AsyncRunner {
        private long requestCount;

        @Override
        public void exec(Runnable code) {
            ++requestCount;
            Thread t = new Thread(code);
            t.setDaemon(true);
            t.setName("NanoHttpd Request Processor (#" + requestCount + ")");
            t.start();
        }
    }

    public static JServer createServer(JMiddleWare middleWare) {
        return new JServer(middleWare);
    }

    public void listen(int port) throws IOException {
            myServerSocket = new ServerSocket();
            myServerSocket.bind(new InetSocketAddress(port));

            myThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    do {
                        try {
                            final Socket finalAccept = myServerSocket.accept();
                            registerConnection(finalAccept);
                            finalAccept.setSoTimeout(SOCKET_READ_TIMEOUT);
                            final InputStream inputStream = finalAccept.getInputStream();
                            asyncRunner.exec(new Runnable() {
                                @Override
                                public void run() {
                                    OutputStream outputStream = null;
                                    try {
                                        outputStream = finalAccept.getOutputStream();
                                        JResponse response = new JResponse(outputStream);
                                        JRequest request = new JRequest(inputStream);

                                        onRequest(request, response);
//                                        TempFileManager tempFileManager = tempFileManagerFactory.create();
//                                        HTTPSession session = new HTTPSession(tempFileManager, inputStream, outputStream, finalAccept.getInetAddress());
//                                        while (!finalAccept.isClosed()) {
//                                            session.execute();
//                                        }

                                    } catch (Exception e) {
                                        // When the socket is closed by the client, we throw our own SocketException
                                        // to break the  "keep alive" loop above.
//                                        if (!(e instanceof SocketException && "NanoHttpd Shutdown".equals(e.getMessage()))) {
//                                            logger.error(e.getMessage(), e);
//                                        }
                                    } finally {
                                        try {
//                                            if (logger.isInfoEnabled()) {
//                                                logger.info(String.format("requestCount: %d, error inputStream: %d", ExecutorAsynRunner.requestCount, errorInputStream));
//                                            }
                                        }catch (Exception e) {

                                        }

                                        safeClose(outputStream);
                                        safeClose(inputStream);
                                        safeClose(finalAccept);
                                        unRegisterConnection(finalAccept);
                                    }
                                }
                            });
                        } catch (IOException e) {
                        }
                    } while (!myServerSocket.isClosed());
                }
            });
            myThread.setDaemon(true);
            myThread.setName("NanoHttpd Main Listener");
            myThread.start();


    }

    public synchronized void registerConnection(Socket socket) {
        openConnections.add(socket);
    }

    /**
     * Registers that a connection has been closed
     *
     * @param socket the {@link java.net.Socket} for the connection.
     */
    public synchronized void unRegisterConnection(Socket socket) {
        openConnections.remove(socket);
    }

    /**
     * Forcibly closes all connections that are open.
     */
    public synchronized void closeAllConnections() {
        for (Socket socket : openConnections) {
            safeClose(socket);
        }
    }

    private static final void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private static final void safeClose(Socket closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private static final void safeClose(ServerSocket closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    private JServer(JMiddleWare middleWare) {
        this.middleWare = middleWare;
    }

    private void onRequest(JRequest request, JResponse response) {
        middleWare.call(request, response);
    }
}
