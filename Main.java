import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        Firewall_class f= new Firewall_class("/Users/supreeth/desktop/Book1.csv");
        System.out.println(f.accept_packet("inbound","udp",54,"192.168.2.1"));
    }
}
