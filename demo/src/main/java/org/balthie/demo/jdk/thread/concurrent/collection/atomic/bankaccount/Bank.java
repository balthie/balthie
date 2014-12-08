/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:22:21
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.atomic.bankaccount;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:22:21
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class Bank implements Runnable
{
    private Account account;
    
    public Bank(Account account)
    {
        super();
        this.account = account;
    }

    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            account.substractAmount(1000);
        }
    }
    
}
