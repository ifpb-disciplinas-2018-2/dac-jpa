package br.edu.ifpb.app;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/01/2019, 10:16:37
 */
public class DateExemplo {

    public static void main(String[] args) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:SS");
        Date a = new Date();
        System.out.println("a: "+formato.format(a));
        Date b = new Date();
        System.out.println("b: "+formato.format(b));
        System.out.println("a.equals(b): " + a.equals(b));
        
        LocalDate hoje = LocalDate.now();
        System.out.println("hoje: "+hoje.toString());
        LocalTime horario = LocalTime.now();
        System.out.println("horario: "+horario.toString());
    }
}
