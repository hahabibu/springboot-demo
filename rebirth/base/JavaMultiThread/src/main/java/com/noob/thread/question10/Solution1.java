package com.noob.thread.question10;

/**
 * 模拟4个窗口进行售票
 * 1.定义售票窗口模拟售票（一个窗口对应一个任务、线程，执行售票操作）
 * 2.使用synchronized同步售票操作的票数减少，避免多线程操作导致数据不一致
 * 3.售票是判断余票是否足够，如果不足则停止售票关闭窗口（线程执行结束）
 */
public class Solution1 {

    // 定义对象锁
    static Object lock = new Object();

    // 剩余总票数
    static int remainTicket = 20;

    /**
     * 模拟购票窗口
     */
    static class TicketWindow implements Runnable{

        private String windowName;

        public TicketWindow(String windowName) {
            this.windowName = windowName;
        }

        @Override
        public void run() {
            while(true){
                // 同步块：操作卖票操作
                synchronized (lock){
                    if(remainTicket > 0){
                        buyTicket();
                    }else{
                        System.out.println("票已售完，窗口" + this.windowName + "关闭");
                        break; // 票已售完，线程结束
                    }
                }
                // 模拟购票后的随机操作
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
         * 购票
         */
        private void buyTicket(){
            System.out.println("窗口" + windowName + "售出一张票，当前余票" + remainTicket);
            remainTicket--;
        }
    }

    public static void main(String[] args) {
        // 开启4个窗口模拟售票
        for (int i = 1; i <= 4; i++) {
            new Thread(new TicketWindow("ticketWindow"+i)).start();
        }
    }
}

