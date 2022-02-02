#include <stdio.h>
#include "stdlib.h"
#include "string.h"
struct node{
    int data;
    struct node *next;
};
struct stack
{
    int maxsize;    // define max capacity of the stack
    int top;
    int *items;
};
struct stack* newStack(int capacity)
{
    struct stack *pt = (struct stack*)malloc(sizeof(struct stack));

    pt->maxsize = capacity;
    pt->top = -1;
    pt->items = (int*)malloc(sizeof(int) * capacity);

    return pt;
}

// Utility function to return the size of the stack
int sizeStack(struct stack *pt) {
    return pt->top + 1;
}

// Utility function to check if the stack is empty or not
int isEmpty(struct stack *pt) {
    return pt->top == -1;                   // or return size(pt) == 0;
}

// Utility function to check if the stack is full or not
int isFull(struct stack *pt) {
    return pt->top == pt->maxsize - 1;      // or return size(pt) == pt->maxsize;
}

// Utility function to add an element `x` to the stack
void push(struct stack *pt, int x)
{
    // check if the stack is already full. Then inserting an element would
    // lead to stack overflow
    if (isFull(pt))
    {
        //printf("Overflow\nProgram Terminated\n");
        exit(EXIT_FAILURE);
    }

    //printf("Inserting %d\n", x);

    // add an element and increment the top's index
    pt->items[++pt->top] = x;
}

// Utility function to return the top element of the stack
int peek(struct stack *pt)
{
    // check for an empty stack
    if (!isEmpty(pt)) {
        return pt->items[pt->top];
    }
    else {
        exit(EXIT_FAILURE);
    }
}

// Utility function to pop a top element from the stack
int pop(struct stack *pt)
{
    // check for stack underflow
    if (isEmpty(pt))
    {
        //printf("Underflow\nProgram Terminated\n");
        exit(EXIT_FAILURE);
    }

   // printf("Removing %d\n", peek(pt));

    // decrement stack size by 1 and (optionally) return the popped element
    return pt->items[pt->top--];
}
//------------------------------------------------------------
void toEnd(struct node **head,int data)
{
    if(*head == NULL)
    {
        struct node *newNode  =(struct node *)malloc(sizeof(struct node));
        newNode->data = data;
        newNode->next = *head;
        *head = newNode;
    }
    else
    {
        struct node *temp = *head;
        while (temp->next!=NULL)
        {
            temp= temp->next;
        }
        struct node *newNode  =(struct node *)malloc(sizeof(struct node ));
        newNode->data = data;
        temp->next = newNode;
        newNode->next = NULL;
    }
}
void print(struct node **head)
{
    struct node *temp = *head;
    while (temp!= NULL)
    {
        printf("%d\n",temp->data);
        temp= temp->next;
    }
}
int size(struct node **head)
{
    struct node *temp = *head;
    int count = 0;
    while (temp!=NULL)
    {
        count++;
        temp=temp->next;
    }
    return count;
}
void reverse(struct node **head)
{
    struct node *prev = NULL;
    struct node *next = NULL;
    struct node *curr = *head;
    while (curr !=NULL)
    {
        next = curr->next;
        curr->next = prev;
        prev = curr;
        curr= next;
    }
    *head = prev;
}
void reverseFirst(struct node **head,int k)
{
  int step = size(head)-k;
  int help=1;
  struct node *temp = *head;
  while (help<step)
  {
      temp=temp->next;
      help++;
  }
  struct node *temp2= temp;
//  struct node *newHead =NULL;
struct stack* stack = newStack(k);
  temp= temp->next;
  while (temp!=NULL)
  {
     // toEnd(&newHead,temp->data);
      push(stack,temp->data);
      temp=temp->next;
  }
  temp2->next=NULL;
//  reverse(&newHead);
//  struct node *temp3 = newHead;
  while (!isEmpty(stack))
  {
      toEnd(head,pop(stack));
  }
}
int getNum(char string[500])
{
    char copy[5000];
    strcat(copy,string);
    char *token=strtok(copy," ");
    while (token!=NULL)
    {
        token = strtok(NULL," ");
        return  atoi(token);
    }
}
int main() {
    struct node *head =NULL;

    toEnd(&head,10);
    toEnd(&head,20);
    toEnd(&head,30);
    toEnd(&head,40);
    toEnd(&head,50);
    reverseFirst(&head,4);
    print(&head);
    return 0;
}
