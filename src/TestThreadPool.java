import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5),new ThreadPoolExecutor.DiscardOldestPolicy());
		
		for(int i = 0;i < 20;i++) {
			MyTask myTask = new MyTask(i);
			executor.execute(myTask);
			System.out.println("线程池中线程数目："+executor.getPoolSize());
			System.out.println("队列中等待执行的任务的数目：" + executor.getQueue().size());
			System.out.println("已执行完的别的任务数：" + executor.getCompletedTaskCount());
		}
		
		executor.shutdown();
	}
	
	
}

class MyTask implements Runnable {
	private int tasknum;
	
	public MyTask(int tasknum) {
		this.tasknum = tasknum;
	}
	
	@Override
	public void run() {
		System.out.println("正在执行task"+tasknum);
		
		try {
			Thread.currentThread().sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("task" + tasknum + "执行完毕");
	}
	
}
