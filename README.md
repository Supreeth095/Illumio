# Illumio challenge
Started: 2pm EST
End: 3:30pm EST


The Firewall_class implements Firewall_Interface.

In the Firewall_class:

Constructor - to initialize the  path to the CSV file

accept_packet method is written.

It  reads each line in the rules CSV file row by row. 

Algorithm :

For each row of data until match is found, 

->code checkes if the direction is a match. If not , it moves on to the next row. 

-> if direction is a match, code checks if protocol is a match. If not , it moves on to the next row. 

-> if protocol is a match, code checks if port is a match. 
    Here it first checks if it is a single port or a range and does its check accordingly. 
    If port is not a match , it moves on to the next row. 
    
-> if port is a match, code checks if IP is a match. 
   Here it first checks if it is a single IP or a range of IPs and does its check accordingly. 
   If IP is not a match , it moves on to the next row. 
   
   If IP is also a match then it returns true. 
   
   
   Valid inputs - Assumed:
   
   direction- "inbound" or "outbound"
   protocol - "tcp" or "udp"
   port- a number or a range of numbers
   IP address -one ip or a range of ip.
   
   
   Functionality:
   1.Yes, Code should work correctly. 
   2. Assuming the CSV file has all valid inputs, there will not be any breaks. 
   3.Yes, I did test it with sample inputs varying all the 4 values. It worked fine.
     
One exception was when I saved the CSV using excel, in my macbook, there were leading spaces generated in the first row, while reading csv file. I reopened it in another application and saved it and the leading space was gone. 
However, if the leading space issue is there, i have added a small if condition check in the code in order to solve this issue.
   
   Performance thoughts: 
   Time:
   
   It has to traverse through all the rows until we find a match. However the checks are in the increasing order of complexity of checks. 
   We first check direction and protocol which are simple character comparison . If that is not satisfied , we do not do the other checks, which is good for optimality. 
   Even though TC is O(n), due to this, it'll be much lesser than O(n).
   
   Space:
   All the new variables created are temporary and space complexity is O(1) as the variables get destroyed once control come out of the if construct. 
   


