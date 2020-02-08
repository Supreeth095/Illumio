import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


//Firewall_class implements the interface
public class Firewall_class implements Firewall_Interface {

    private String absolute_path_to_csv_file;

    //Constructor to initialize the  path to the CSV file
    public Firewall_class(String path) {
        absolute_path_to_csv_file=path;
    }

    @Override
    public boolean accept_packet(String direction, String protocol, int port, String ip_address) throws UnknownHostException{
        //String variable to store each row in the firewall rules file
        String each_line = "";
        boolean first_row=true;



        try (BufferedReader br = new BufferedReader(new FileReader(absolute_path_to_csv_file))) {

            //Reading each line in the rules file
            while ((each_line = br.readLine()) != null) {
                //for the bug that adds leading spaces to the beginning of the first line
                if(first_row) {
                    if(each_line.charAt(0)!='u' && each_line.charAt(0)!='t'){
                        each_line=each_line.substring(1,each_line.length());

                    }
                    first_row=false;
                }


                // store the current record's rule parameters
                String[] each_firewall_rule = each_line.split(",");



                //Check if the given direction matches with the current row's rule's direction. If not go to the next row in the CSV.
                if(!(each_firewall_rule[0].charAt(0)==direction.charAt(0))){
                    continue;
                }

                //If check of direction is matched, then the control for the code reaches here. Else it loops back to the next row in the csv
                //Check if the given protocol matches with the current row's rule's protocol. If not go to the next row in the CSV.
                if(!(each_firewall_rule[1].charAt(0)==protocol.charAt(0))){
                    continue;
                }

                //If check of direction and protocol is matched, then the control for the code reaches here. Else it loops back to check the next row in the csv
                //Check if the given port matches with the current row's rule's port. If not go to the next row in the CSV.
                String[] ports_range= new String[2];

                //check if it is single port or multiple ports

                //code for single port check
                if(!(each_firewall_rule[2].contains("-"))) {
                    if (port != Integer.parseInt(each_firewall_rule[2])) {
                        continue;

                    }
                }
                //code for a range of ports check
                else {
                    ports_range=each_firewall_rule[2].split("-");
                    if(port<Integer.parseInt(ports_range[0]) || port>Integer.parseInt(ports_range[1]) ){
                        continue;
                    }
                }


                //If check of direction and protocol and ports is matched, then the control for the code reaches here. Else it loops back to check the next row in the csv
                //Check if the given IP matches with the current row's rule's IP. If not go to the next row in the CSV.

                //code for a single IP check
                if(!(each_firewall_rule[3].contains("-"))){

                    if(!(each_firewall_rule[3].equals(ip_address))){
                        continue;
                    }


                }

                //code for a range of IPs check
                else{

                    String[] octets= new String[2];
                    octets= each_firewall_rule[3].split("-");

                    long ipLo = ipToLong(InetAddress.getByName(octets[0]));
                    long ipHi = ipToLong(InetAddress.getByName(octets[1]));
                    long ipToTest = ipToLong(InetAddress.getByName(ip_address));

                    if(ipToTest < ipLo || ipToTest > ipHi)
                        continue;
                }




                //If all of these 4 checks are matched, then the control for the code reaches here.
                return true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //If All 4 checks are never matched in the rules file, then the control for the code reaches here.
        return false;
    }

    public static long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }
}
