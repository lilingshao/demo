package com.example.demo.zookeeperQuene;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.Serializable;

public class ZKQueueTest {

	public static void main(String[] args) throws Exception{
		ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000, 5000, new SerializableSerializer());
		DistributedSimpleQueue<SendObject> queue = new DistributedSimpleQueue<SendObject>(zkClient, "/Queue");
//		new Thread(new ConsumerThread(queue)).start();

		Thread t1 = new Thread(new ProducerThread(queue));
		t1.start();
		t1.join();
		System.out.println("queue.size=="+queue.size());
	}

}

class ConsumerThread implements Runnable {
	private DistributedSimpleQueue<SendObject> queue;

	public ConsumerThread(DistributedSimpleQueue<SendObject> queue) {
		this.queue = queue;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep((int) (Math.random() * 100));// ���˯��һ��
				SendObject sendObject = (SendObject) queue.poll();
				System.out.println("����һ����Ϣ�ɹ���" + sendObject);
			} catch (Exception e) {
			}
		}
	}
}

class ProducerThread implements Runnable {

	private DistributedSimpleQueue<SendObject> queue;

	public ProducerThread(DistributedSimpleQueue<SendObject> queue) {
		this.queue = queue;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep((int) (Math.random() * 100));// ���˯��һ��
				SendObject sendObject = new SendObject(String.valueOf(i), "content" + i);
				queue.offer(sendObject);
				System.out.println("����һ����Ϣ�ɹ���" + sendObject);
			} catch (Exception e) {
			}
		}
	}

}

class SendObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public SendObject(String id, String content) {
		this.id = id;
		this.content = content;
	}

	private String id;

	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SendObject [id=" + id + ", content=" + content + "]";
	}

}