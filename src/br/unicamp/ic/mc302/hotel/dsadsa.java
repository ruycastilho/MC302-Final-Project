/* package whatever; // don't place package name! */

import java.io.*;

class Teste2
{
    
    public static void main (String[] args) throws java.lang.Exception
    {
        // Input reading
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int integer_quantity=0;
        
        
        try {
            integer_quantity = Integer.parseInt(br.readLine());

            // In case N = 0 or N = 1
            if ( integer_quantity == 0 || integer_quantity == 1 ) {
                System.out.println(0);

            }
            else {

                // Initial smallest value
                int menor = Integer.parseInt(br.readLine());

                // Initial biggest value
                int maior = Integer.parseInt(br.readLine());



                // In case they're swapped
                if (menor > maior) {
                    int swap = menor;
                    menor = maior;
                    maior = swap;

                }

                int current_integer= 0;

                // Compares new entries with stored numbers and changes them accordingly
                for (int i=2; i < integer_quantity; i++) {

                    current_integer= Integer.parseInt(br.readLine());

                    if ( current_integer > maior ) {
                        maior = current_integer;

                    }
                    else if ( current_integer < menor) {
                        menor = current_integer;

                    } 
                }

                // Final result
                System.out.println(maior-menor);
            }   
        }
        // In case of errors
        catch (IOException error) {
            System.out.println(0);
        }
        catch (java.lang.NumberFormatException error2) {
            System.out.println(0);

        }

        


        
    }
    
}


import java.io.*;

class Test1
{
    public static void main (String[] args) throws java.lang.Exception
    {
        
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Integer seconds = Integer.parseInt(br.readLine());

            // Calculates initial values for minutes, hours and days
            int minutes = seconds/60;    // Conversion between minutes and seconds
            int hours = minutes/60;    // Conversion between hours and minutes
            int days = hours/24;    // Conversion between days and hours

            // Obtains the amounts not absorbed by larger time periods
            seconds = seconds % 60;
            minutes = minutes % 60;
            hours = hours % 24;
            
            // Final result
            System.out.println(String.format("%02d", days) + "d" + 
                               String.format("%02d", hours) + "h" +
                               String.format("%02d", minutes) + "m" +
                               String.format("%02d", seconds) + "s");
            
        }

        // In case of errors
        catch (IOException error) {
            System.out.println("ERRO");
        }
        catch (java.lang.NumberFormatException error2) {
            System.out.println("ERRO");

        }
        
        
    }
}
