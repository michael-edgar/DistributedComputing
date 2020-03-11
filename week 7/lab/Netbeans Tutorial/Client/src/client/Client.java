/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author t00036478
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        int i = 3;
    int j = 4;
    int result = add(i, j);
    System.out.println("Result = " + result);
        
    }
        catch(Exception ex){
            System.out.println("Exception: " + ex);
        }
    }
    private static int add(int i, int j) {
        client.CalculatorWS_Service service = new client.CalculatorWS_Service();
        client.CalculatorWS port = service.getCalculatorWSPort();
        return port.add(i, j);
    }
    
}
