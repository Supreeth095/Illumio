import java.net.UnknownHostException;

public interface Firewall_Interface {
    boolean accept_packet(String direction, String protocol, int port, String ip_address ) throws UnknownHostException;
}
