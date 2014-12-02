/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:21:03
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.atomic.bankaccount;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:21:03
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class Company implements Runnable
{
    private Account account;

    public Company(Account account)
    {
        super();
        this.account = account;
    }

    @Override
    public void run()
    {
       for(int i=0;i<10;i++)
       {
           account.addAccount(1000);
       }
        
    }
    
    
}
