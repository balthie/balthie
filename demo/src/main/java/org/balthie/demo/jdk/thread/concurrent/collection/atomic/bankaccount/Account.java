/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:17:52
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.collection.atomic.bankaccount;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年12月3日 上午10:17:52
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class Account
{
    private AtomicLong balance;

    public Account()
    {
        super();
        this.balance = new AtomicLong();
    }

    public void addAccount(long amount)
    {
        // 增加
        this.balance.getAndAdd(amount);
    }
    
    public void substractAmount(long amount)
    {
        this.balance.getAndAdd(-amount);
    }
    
    public AtomicLong getBalance()
    {
        return balance;
    }

    public void setBalance(long balance)
    {
        this.balance.set(balance);
    }
    
}
