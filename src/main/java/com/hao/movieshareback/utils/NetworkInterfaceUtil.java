package com.hao.movieshareback.utils;

import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class NetworkInterfaceUtil {
    private static List<NetworkInterface> getInterfaces() throws SocketException {
        return Collections.list(NetworkInterface.getNetworkInterfaces());
    }

    private static List<NetworkInterface> getNonLoopBackInterfaces() throws SocketException {
        return getInterfaces().stream()
                .filter(i -> {
                    try {
                        return i.isLoopback();
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(toList());
    }

    public static List<String> getIp4Addresses() throws SocketException {
        final List<NetworkInterface> is = getNonLoopBackInterfaces();
        return is.stream().flatMap(i -> {
            final Enumeration<InetAddress> addresses = i.getInetAddresses();
            final Stream.Builder<String> builder = Stream.builder();
            while (addresses.hasMoreElements()) {
                final InetAddress ip = addresses.nextElement();
                if (!ip.isLoopbackAddress()) {
                    if (ip.getHostAddress().equalsIgnoreCase("127.0.0.1")) {
                        continue;
                    }
                    if (ip instanceof Inet6Address) {
                        continue;
                    }
                    if (ip instanceof Inet4Address) {
                        builder.add(ip.getHostAddress());
                    }
                }
            }
            return builder.build();
        }).collect(toList());
    }
}
