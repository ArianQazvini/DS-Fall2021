#include <stdio.h>
#include "stdlib.h"
#include "string.h"
#include "stdbool.h"
struct node{
    int data;
    struct node *next;
};
struct node *head = NULL;
bool isReversed = false;
void toEnd(int data)
{
    if(head == NULL)
    {
        struct node *newNode  =(struct node *)malloc(sizeof(struct node));
        newNode->data = data;
        newNode->next = head;
        head = newNode;
    }
    else
    {
        struct node *temp = head;
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
void toFront(int data)
{
    struct node *newNode  =(struct node *)malloc(sizeof(struct node ));
    newNode->data = data;
    newNode->next = head;
    head = newNode;
}
int front()
{
        int temp2 = head->data;
        struct node *temp = head;
        if(temp->next==NULL)
        {
            free(temp);
            temp=NULL;
            head=NULL;
            return temp2;
        }
        else
        {
            head = head->next;
            free(temp);
            temp= NULL;
            return temp2;
        }
}
int back()
{
    int temp2 = head->data;
    struct node *temp = head;
    if(temp->next==NULL)
    {
        free(temp);
        temp=NULL;
        head=NULL;
        return temp2;
    }
    else
        {
            struct node *prev = head;
            struct node *curr = prev->next;
            while (curr->next !=NULL)
            {
                curr = curr->next;
                prev= prev->next;
            }
            int temp = curr->data;
            prev->next = NULL;
            free(curr);
            curr=NULL;
            return temp;
        }
}
void print()
{
    struct node *temp = head;
    while (temp!= NULL)
    {
        printf("%d\n",temp->data);
        temp= temp->next;
    }
}
void reverse()
{
    if(isReversed)
    {
        isReversed=false;
    }
    else
    {
        isReversed=true;
    }
//    struct node *prev = NULL;
//    struct node *next = NULL;
//    while (head !=NULL)
//    {
//        next = head->next;
//        head->next = prev;
//        prev = head;
//        head = next;
//    }
//    head = prev;
}
int size()
{
    struct node *temp = head;
    int count = 0;
    while (temp!=NULL)
    {
        count++;
        temp=temp->next;
    }
    return count;
}
int getNum(char string[5000])
{
    char copy[500];
    strcat(copy,string);
    char *token=strtok(copy," ");
    while (token!=NULL)
    {
        token = strtok(NULL," ");
        return  atoi(token);
    }
}
void createCycle(int EndIndex)
{
    struct node *pt1 = head;
    struct node *pt2 = head;
    int j=0;
    while (j!=EndIndex)
    {
        pt1=pt1->next;
        j++;
    }
    while (pt2->next!=NULL)
    {
        pt2=pt2->next;
    }
    pt2->next= pt1;
}
bool hasCycle()
{
    struct node *focus = (struct node * )malloc(sizeof(struct node));
    while (head!=NULL)
    {
        if(head->next==focus)
        {
            return true;
        }
        else
        {
            struct node *next = head->next;
            head->next= focus;
            head = next;
        }
    }
    return false;
}
int main() {
    toFront(10);
    toFront(20);
    toFront(30);
    toFront(40);
    toFront(50);
    toFront(60);
    toEnd(70);
    createCycle(3);

    if(hasCycle())
    {
        printf("1");
    }


    return 0;
}
