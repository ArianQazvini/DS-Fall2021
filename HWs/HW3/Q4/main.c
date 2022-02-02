#include <stdio.h>
#include "stdlib.h"
#include "stdbool.h"
struct node{
    int data;
    int repeat;
    struct node *left;
    struct node *right;
    struct node *p;
};

struct node *newNode(int data)
{
    struct node *temp= (struct node *) malloc(sizeof(struct node));
    temp->data = data;
    temp->repeat=1;
    temp->left = temp->right =temp->p= NULL;
    return temp;
}
void swap(struct node *a,struct node *b)
{
    int temp = a->data;
    a->data = b->data;
    b->data=temp;
    //-------------------
    int temp2 = a->repeat;
    a->repeat=b->repeat;
    b->repeat=temp2;
}
struct node *getaLeaf(struct node *root)
{
    struct node *temp = root;
    while (temp->left !=NULL || temp->right!=NULL)
    {
        if(temp->left ==NULL && temp->right !=NULL)
        {
            temp= temp->right;
        }
        else if(temp->left!=NULL && temp->right==NULL)
        {
            temp=temp->left;
        }
        else if(temp->right!=NULL && temp->right!=NULL)
        {
            temp = temp->right;
        }
    }
    return temp;
}
void minHeapify(struct node *node)
{
    if(node->p!=NULL)
    {
        if(node->p->data > node->data)
        {
            swap(node->p,node);
            minHeapify(node->p);
        }
    }
}
bool checkRepeats(struct node *root,int key)
{
    if(root->left==NULL && root->right==NULL)
    {
        if(root->data==key)
        {
            root->repeat++;
            return true;
        }
        else
        {
            return false;
        }
    }
    else if(root->left ==NULL && root->right !=NULL)
    {
        checkRepeats(root->right,key);
    }
    else if(root->left !=NULL && root->right ==NULL)
    {
        checkRepeats(root->left,key);
    }
    else
    {
        checkRepeats(root->left,key);
        checkRepeats(root->right,key);
    }
}
int size=0;
struct node *insert(struct node *root,int data)
{
  if(root==NULL)
  {
      root = newNode(data);
      size++;
      return root;
  }
  else
  {
      bool check = checkRepeats(root,data);
      if(!check)
      {

          if(root->left ==NULL && root->right !=NULL)
          {
              root->left= newNode(data);
              root->left->p=root;
              minHeapify(root->left);
              size++;
          }
          else if(root->left!=NULL && root->right==NULL)
          {
              root->right=newNode(data);
              root->right->p=root;
              minHeapify(root->right);
              size++;
          }
          else if(root->left==NULL && root->right==NULL)
          {
              root->right=newNode(data);
              root->right->p=root;
              minHeapify(root->right);
              size++;
          }
          else if(root->right!=NULL && root->right!=NULL)
          {
              root->right = insert(root->right,data);
          }
      }
      return root;
  }
}
void minHeapify2(struct node *node) {
    if (node == NULL || node->left == NULL)
        return;
    struct node *min = node->left;
    if (node->right != NULL && min->data > node->right->data) {
        min = node->right;
    }
    if (min->data < node->data) {
        swap(node,min);
        minHeapify2(min);
    }
}
void print(struct node *root)
{
    while (size>0)
    {
        for (int i = 0; i < root->repeat; ++i) {
            printf("%d ",root->data);
        }
        struct node *replace = getaLeaf(root);
        if(replace== replace->p->right)
        {
            replace->p->right=NULL;
        }
        else if(replace==replace->p->left)
        {
            replace->p->left=NULL;
        }
        if(root->left!=NULL)
        {
            root->left->p=replace;
            replace->left=root->left;

        }
        if(root->right!=NULL)
        {
            root->right->p=replace;
            replace->right=root->right;
        }
        root=replace;
        minHeapify2(root);
        size--;
    }
}
bool isBST(struct node *root)
{
    if(root !=NULL)
    {
        if(root->right!=NULL )
        {
            if(root->right->data<root->data)
            {
                return false;
            }
        }
        if(root->left!=NULL)
        {
            if(root->left->data>root->data)
            {
                return false;
            }
        }
        bool b1 = isBST(root->right);
        bool b2 = isBST(root->left);
        if(b1 && b2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    return true;
}
void start(int n,char inputs[n][5000])
{
    struct node *root =NULL;
    for (int i = 0; i < n; ++i) {
        char first[500];
        int j=0;
        int f=0;
        while (inputs[i][j]!='\0' && inputs[i][j]!='\n')
        {
            first[f]=inputs[i][j];
            j++;
            f++;
            if(inputs[i][j]==' ')
            {
                first[f]='\0';
                root=insert(root,atoi(first));
                f=0;
                j++;
            }
            else if(inputs[i][j]=='\0' || inputs[i][j]=='\n')
            {
                first[f]='\0';
                root=insert(root,atoi(first));
            }
        }
    }
    print(root);
}
int main() {

//    int k ;
//    scanf("%d\n",&k);
//    char inputs[k][5000];
//    for (int i = 0; i < k; ++i) {
//        fgets(inputs[i],5000,stdin);
//    }
//    start(k,inputs);
//    struct node *root=NULL;
//    root =insert(root,5);
//    root =insert(root,7);
//    root =insert(root,1);
//    root =insert(root,99);
//    root =insert(root,3);
//    root =insert(root,2);
//    if(isBST(root))
//    {
//        printf("Y");
//    }
//    else
//    {
//        printf("N");
//    }
   int x = 5 %2 + 6;
   printf("%d ",x);
   return 0;
}

