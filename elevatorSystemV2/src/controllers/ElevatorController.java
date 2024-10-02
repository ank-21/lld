package controllers;

import enums.Direction;
import models.Elevator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ElevatorController {
    private int id;
    private int maxCapacity;
    private Elevator elevator;

    PriorityQueue<Integer> upMinPQ;
    PriorityQueue<Integer> downMaxPQ;
    Queue<Integer> pendingUpOperations;
    Queue<Integer> pendingDownOperations;

    public ElevatorController(int id, int maxCapacity) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.elevator = new Elevator(id, maxCapacity);

        upMinPQ = new PriorityQueue<>();
        downMaxPQ = new PriorityQueue<>(Collections.reverseOrder());
        pendingUpOperations = new LinkedList<>();
        pendingDownOperations = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void acceptExternalRequest(int floorNo, Direction dir){

    }
}
