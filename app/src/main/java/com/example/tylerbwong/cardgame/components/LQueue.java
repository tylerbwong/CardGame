package com.example.tylerbwong.cardgame.components;

import android.os.Parcel;
import android.os.Parcelable;

public class LQueue<T> implements Parcelable{
   // LQueue instance variables
   private Node front;
   private Node end;

   // constructor
   public LQueue() {
      front = null;
      end = null;
   }

   // Node class
   private class Node {
      public T data;
      public Node next;
   }

   // Exception class
   public static class MyException extends RuntimeException {
      public MyException() {
         super();
      }

      public MyException(String message) {
         super(message);
      }
   }

   /*
    * Function: enqueue
    * Description: adds T type element to end of the queue
    * Parameters: o - a parameter of type T
    * Return: N/A
    */
   public void enqueue(T o) {
      Node oldEnd = end;
      end = new Node();
      end.data = o;

      if (isEmpty()) {
         front = end;
      } else {
         oldEnd.next = end;
      }
   }

   /*
    * Function: dequeue
    * Description: front element is removed, otherwise exception is thrown
    * Parameters: N/A
    * Return: a T type value
    */
   public T dequeue() throws MyException {
      if (isEmpty()) {
         throw new MyException();
      }

      T release = front.data;
      front = front.next;
      if (isEmpty()) {
         end = null;
      }
      return release;
   }

   /*
    * Function: peekFront
    * Description: shows first element
    * Parameters: N/A
    * Return: a T type value
    */
   public T peekFront() {
      if (isEmpty()) {
         throw new MyException();
      }
      return front.data;
   }

   /*
    * Function: peekEnd
    * Description: shows last element
    * Parameters: N/A
    * Return: a T type value
    */
   public T peekEnd() {
      if (isEmpty()) {
         throw new MyException();
      }
      return end.data;
   }

   /*
    * Function: isEmpty
    * Description: true if queue is empty, false otherwise
    * Parameters: N/A
    * Return: boolean
    */
   public boolean isEmpty() {
      return front == null;
   }

   /*
    * Function: size
    * Description: gets the size of LQueue
    * Parameters: N/A
    * Return: an int data type
    */
   public int size() {
      int size = 1;
      for (Node n = front; n.next != null; n = n.next) {
         size++;
      }
      return size;
   }

   protected LQueue(Parcel in) {
      front = (Node) in.readValue(Node.class.getClassLoader());
      end = (Node) in.readValue(Node.class.getClassLoader());
   }

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeValue(front);
      dest.writeValue(end);
   }

   @SuppressWarnings("unused")
   public static final Parcelable.Creator<LQueue> CREATOR = new Parcelable.Creator<LQueue>() {
      @Override
      public LQueue createFromParcel(Parcel in) {
         return new LQueue(in);
      }

      @Override
      public LQueue[] newArray(int size) {
         return new LQueue[size];
      }
   };
}
