//package com.hubberspot.dsalgo.list;

import java.util.NoSuchElementException;
import java.util.*; 


public class CircularLinkedList {
  static private ListNode last;
  static private int length;
  
  private static class ListNode {
    private ListNode next;
    private String data;
    
    public ListNode(String data) {
      this.data = data;
    }
  }
  
  public CircularLinkedList() {
    last = null;
    length = 0;
  }
  
  public int length() {
    return length;
  }
  
  public boolean isEmpty() {
    return length == 0;
  }
  
  public void createCircularLinkedList(int n) {
    if(n>=0) {
      length = n;
      Scanner sc = new Scanner(System.in);
      ListNode first = new ListNode(" ");
      ListNode current;
      ListNode temp = new ListNode(" ");
      for(int i = 1; i <= n; i++) {
        String bus_n;
        System.out.print("Enter " + i + "-bus: ");
        bus_n = sc.nextLine();
        if(i == 1) {
          first =  new ListNode(bus_n);
          temp = first;
        } else {
          current = new ListNode(bus_n);
          temp.next = current;
          temp = current;
        }
      }
      temp.next = first;
      last = temp;
    } else {
      System.out.print("Cannot be zero or negative");
    }
  }
  
  public void display() {
    if(last == null) {
      return;
    }
    ListNode first = last.next;
    while(first != last) {
      System.out.print(first.data + " --> ");
      first = first.next;
    }
    System.out.println(first.data);
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Scanner sc_t = new Scanner(System.in);
    CircularLinkedList cll = new CircularLinkedList();
    List<String> rest_buses = new ArrayList<String>();
    int l = 0;
    String time;
    while(true) {
      System.out.print("\n\nLocal Bus Station Dispatch Center!\n1.Start the day\n2.Quit pick hours\n3.Enter Pick hours\n4.Display current buses\n5.Buses in depo\n6.End the day");
      System.out.print("\nChoose: ");
      l = sc.nextInt();
      switch(l) {
        case 1: 
          System.out.print("How many buses will service public today?\nNumber: ");
          l = sc.nextInt();
          cll.createCircularLinkedList(l);
          System.out.print("Starting buses: ");
          cll.display();
          break;
        case 2:
          System.out.print("What time is it now?\nTime: ");
          time = sc_t.nextLine();
          if(time.equals(new String("10AM"))) {
            ListNode temp = new ListNode(" ");
            ListNode first = last.next;
            ListNode next_first = first;
            first = first.next;
            int array_ind = 0;
            boolean isStart = true;
            int limit = length - 1;  
            int new_length = 1;        
            for(int i = 2; i < length; i++) {
              if(i%2 == 1) {
                 if(isStart) {
                  next_first.next = first;
                  temp = first;
                  isStart = false;
                  } else {
                    if(i == limit) {
                      temp.next = first;
                      temp = first;
                      rest_buses.add(array_ind, temp.next.data);                      
                      temp.next = next_first;                      
                    } else {
                      temp.next = first;
                      temp = first;
                    }
                  }
                  new_length++;
                } else {
                  rest_buses.add(array_ind, first.data);
                  array_ind++;
                }            
              first = first.next;
            }      
            length = new_length;
            last = temp;  
            System.out.print("Buses after pick hours: ");
            cll.display();
          } else {
            System.out.print("Pick hours are still active!!!\nCannot remove buses!!!!");
          }
          break;
        case 3: 
          System.out.print("What time is it now?\nTime: ");
          time = sc_t.nextLine();
          if(time.equals(new String("5PM"))) {
            ListNode back_bus = new ListNode(" ");
            ListNode temp = new ListNode(" ");
            ListNode current = new ListNode(" ");
            ListNode first = last.next;
            String depo_bus = rest_buses.get(0);
            rest_buses.remove(0);
            back_bus = new ListNode(depo_bus);
            current = first.next;
            first.next = back_bus;
            for(int i = 1; i < length; i++) {           
              temp = current;
              current = current.next;  
              back_bus.next = temp;              
              depo_bus = rest_buses.get(0);
              rest_buses.remove(0);
              back_bus = new ListNode(depo_bus);
              temp.next = back_bus;  
            }
              length *= 2;
              back_bus.next = first;
              last = back_bus;
              System.out.print("Buses after entering pick hours: ");
              cll.display();
            } else {
            System.out.print("Pick hours are still active!!!\nCannot remove buses!!!!");
          }
          break;
        case 4: 
          System.out.print("Currently " + length + " buses are running: ");
          cll.display();
          break;
        case 5: 
          System.out.print(rest_buses.size() + " buses in the depo right now: ");
          System.out.print(rest_buses);
          break;
        case 6: 
          ListNode first = last.next;
          for(int i = 1; i<=length; i++) {
            rest_buses.add(first.data);   
            first = first.next;
          }
          length = 0;
          last = null;
          break;
        }
      }
  }
}
