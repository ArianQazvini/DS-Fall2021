#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct node{
    int data;
    struct node *left;
    struct node *right;
};
struct node *newNode(int data)
{
    struct node *newnode = (struct node *)malloc(sizeof(struct node));
    newnode->data=data;
    newnode->left=NULL;
    newnode->right=NULL;
    return newnode;
}
struct node *balancedBST(int *arr,int startIndex,int endIndex)
{
    if(startIndex>endIndex)
    {
        return NULL;
    }
    int midIndex = (startIndex+endIndex)/2;
    struct node *root = newNode(*(arr+midIndex));
    root->right=balancedBST(arr,midIndex+1,endIndex);
    root->left=balancedBST(arr,startIndex,midIndex-1);
    return root;

}
int depth(struct node *root)
{
    if(root==NULL)
    {
        return 0;
    }
    else
    {
        int lheight = depth(root->left);
        int rheight = depth(root->right);
        if(lheight>rheight)
        {
            return lheight+1;
        }
        else
        {
            return rheight+1;
        }
    }
}
void printLevel(struct node *root,int level)
{
    if(root==NULL)
    {
        printf("null ");
        return;
    }
    if(level==1)
    {
        printf("%d ",root->data);
    }
    else
    {
        printLevel(root->left,level-1);
        printLevel(root->right,level-1);
    }
}
void levelOrder(struct node *root)
{
    for (int i = 1; i <=depth(root) ; ++i) {
      printLevel(root,i);
    }
}
void start(char string[1000])
{
    int arr[200];
    char first[50];
    int j=0;
    int z=0;
    int f=0;
    while (string[j]!='\n')
    {
        first[f]=string[j];
        j++;
        f++;
        if(string[j]==' ')
        {
            first[f]='\0';
            arr[z]=atoi(first);
            f=0;
            j++;
            z++;
        }
        else if(string[j]=='\n')
        {
            first[f]='\0';
            arr[z]=atoi(first);
            z++;
            break;
        }
    }
    struct node *root = balancedBST(arr,0,z-1);
    levelOrder(root);
    printf("\n");
}
int main() {
    char nums[1000];
    fgets(nums,1000,stdin);
    start(nums);
    return 0;
}
