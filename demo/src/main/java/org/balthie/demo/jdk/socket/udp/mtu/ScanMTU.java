package org.balthie.demo.jdk.socket.udp.mtu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * @author chencheng
 * @version 1.0.0
 * @2008-8-12 上午09:16:58
 */
public class ScanMTU {
 
    private String ip;
 
    private String parameter;
 
    public static void main(String args[]) {
 
        ScanMTU mtu = new ScanMTU();
 
        /*if (args == null || args.length <= 0) {
            System.out.println("Please input dest ip.");
            return;
        } else
            mtu.ip = args[0];*/
 
        mtu.ip = "42.120.245.2";
        try {
            mtu.checkIp(mtu.ip);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
 
        System.out.println("Test Result: Maximum Transmission Unit(MTU): " + mtu.scan() + " Bytes Free.");
 
    }
 
    private int scan() {
        for (int length = 1500; length > 0; length--) {
            try {
                Process pro = Runtime.getRuntime().exec(setCommand(ip, length));
                BufferedReader buf = new BufferedReader(new InputStreamReader(pro.getInputStream()));
 
                int count = 0;
                String line = null;
                while ((line = buf.readLine()) != null) {
                    if (!line.equals("")) {
                        System.out.println(line);
                        System.out.println(new String(line.getBytes("gb2312")));
                        if (count++ == 1) {
                            if (line.toLowerCase().indexOf("df") == -1)
                                return length;
                            break;
                        }
                    }
                }
                buf.close();
 
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return -1;
    }
 
    private String setCommand(String ipAddress, int fragSize) {
        if (checkForLinux())
            parameter = "ping -M do -s " + fragSize;
        else if (checkForWindows()) {
            parameter = "ping -f -l " + fragSize;
        }
        return parameter + " " + ipAddress;
    }
 
    /**
     * 检测输入的IP格式是否正确
     * 
     * @return
     * @throws Exception
     */
    private void checkIp(String ipAddress) throws Exception {
        String regex = "[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}";
        if (!ipAddress.matches(regex) && !ipAddress.equalsIgnoreCase("localhost")) {
            throw new Exception("Input Error!");
        }
    }
 
    /**
     * 检查当前操作系统是否为linux
     * 
     * @return
     */
    private boolean checkForLinux() {
        String os = System.getProperty("os.name");
        return os != null && os.toLowerCase().startsWith("linux") ? true : false;
    }
 
    /**
     * 检查当前操作系统是否为windows
     * 
     * @return
     */
    private boolean checkForWindows() {
        String os = System.getProperty("os.name");
        return os != null && os.toLowerCase().startsWith("win") ? true : false;
    }
}