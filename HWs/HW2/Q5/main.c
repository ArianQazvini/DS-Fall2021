#include <stdio.h>
#include <stdlib.h>
#include "string.h"
#include <inttypes.h>
#include "stdbool.h"
struct node{
    int data;
   // int index;
    struct node *npx;
};
struct node *head = NULL;
struct node *tail = NULL;
struct node *start=NULL;
struct node *startPrev=NULL;
bool startisFound = false;
int size=0;
int k;
struct node *XOR(struct node *a,struct node *b)
{
    return (struct node*) ((uintptr_t ) (a) ^ (uintptr_t) (b));
}
void toEnd(int data)
{
    struct node *newNode = (struct node *)malloc(sizeof(struct node));
    size++;
    newNode->data = data;
    if(head==NULL)
    {
        newNode->npx = head;
        head=newNode;
        tail=head;
    }
    else
    {
        newNode->npx=XOR(tail,NULL);
        tail->npx=XOR(XOR(NULL,tail->npx),newNode);
        tail=newNode;
    }
    if(!startisFound)
    {
        if(k==size)
        {
            start=head;
            startisFound=true;
        }
    }
    else
    {
        struct node *temp =start;
        start= XOR(startPrev,start->npx);
        startPrev=temp;
    }
}
void print()
{
    struct node *curr = head;
    struct node *prev = NULL;
    struct node *next;
    while (curr!=NULL)
    {
        printf("%d ",curr->data);
        next =XOR(prev,curr->npx);
        prev=curr;
        curr=next;
    }
    printf("\n");
}
void reverseLastK2(int k)
{
    if(size>0 && k!=1)
    {
                 if(startPrev==NULL)
                 {
                     struct node *temp = head;
                     head=tail;
                     tail=temp;
                     start=head;
                 }
                 else
                 {
                     struct node *tailtemp = tail;
                     tail->npx=XOR(startPrev,XOR(NULL,tail->npx));
                     start->npx=XOR(XOR(start->npx,startPrev),NULL);
                     startPrev->npx= XOR(tail,XOR(startPrev->npx,start));
                     tail=start;
                     start=tailtemp;
                 }
        }
}
int getNum(char string[50])
{
    char copy[50];
    strcat(copy,string);
    char *token=strtok(copy," ");
    while (token!=NULL)
    {
        token = strtok(NULL," ");
        char *ptr;
        int data = strtol(token,&ptr,10);
        return data;
    }
}
int main() {
    int n,kk;
    scanf("%d %d\n",&n,&kk);
    k=kk;
    char commands[50];
    for (int i = 0; i < n; ++i) {
        gets(commands);
        if (commands[0] == 'A') {
            toEnd(getNum(commands));
        } else if (commands[0] == 'R') {
            reverseLastK2(k);
        }
    }
     print();
    return 0;

}
