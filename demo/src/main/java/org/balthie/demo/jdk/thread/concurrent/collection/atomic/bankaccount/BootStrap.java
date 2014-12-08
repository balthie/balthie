/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:23:42
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.atomic.bankaccount;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:23:42
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class BootStrap
{
    public static void main(String[] args)
    {
        Account account = new Account();
        account.setBalance(0L);
        
        Company comp = new Company(account);
        Thread tcomp = new Thread(comp);
        
        Bank bank = new Bank(account);
        Thread tBank = new Thread(bank);
        
        System.out.println(" account init balance = " + account.getBalance());
        
        tcomp.start();
        tBank.start();
        
        
        try
        {
            tcomp.join();
            tBank.join();
            System.out.println(" account final balance = " + account.getBalance());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        
        
        
        
    }
}
