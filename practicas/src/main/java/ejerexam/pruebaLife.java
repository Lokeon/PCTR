package ejerexam;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;

public class pruebaLife
{
   public static void main(String[] args)
   {
       Life.inciarMatriz();

       ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 6000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
   
        for(int iter = 0 ; iter < 100 ; ++iter)
        {
            for(int i = 0 ; i < 10 ; ++i)
            {
                pool.execute(new Life(i));
            }
        }
        
        pool.shutdown();
        while(!pool.isTerminated()){}
    }
}