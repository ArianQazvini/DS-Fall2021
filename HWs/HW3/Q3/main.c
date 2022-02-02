#include <stdio.h>
#include "stdlib.h"
struct guest{
    long coin;
    int start;
    int end;
};
struct guest *create(char string[5000])
{
    long arr[3];
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
            arr[z]=atol(first);
            f=0;
            j++;
            z++;
        }
        else if(string[j]=='\n')
        {
            first[f]='\0';
            arr[z]=atol(first);
            z++;
            break;
        }
    }
    //-----------------------
    struct guest* temp = (struct guest* )malloc(sizeof(struct guest));
    temp->coin=arr[2];
    temp->start=arr[0];
    temp->end=arr[1];
    return temp;
}

int main() {
    int n,mins;
    scanf("%d %d\n",&n,&mins);
    struct guest *guests[n];
    char inputs[n][5000];
    for (int i = 0; i < n; ++i) {
        fgets(inputs[i],5000,stdin);
        guests[i]= create(inputs[i]);
    }
    long time[mins];
    for (int i = 0; i < mins; ++i) {
        time[i]=0;
    }
//    for (int i = 0; i < mins; ++i) {
//        for (int j = 0; j < n; ++j) {
//            if((i+1) >= guests[j]->start && (i+1) <= guests[j]->end)
//            {
//                if(time[i] < guests[j]->coin)
//                {
//                    time[i]=guests[j]->coin;
//                }
//            }
//        }
//    }
    for (int i = 0; i < n; ++i) {
        for (int j = guests[i]->start-1; j < guests[i]->end; ++j) {
            if(time[j]<guests[i]->coin)
            {
                time[j]=guests[i]->coin;
            }
        }
    }
    for (int i = 0; i < mins; ++i) {
        printf("%ld ",time[i]);
    }
    return 0;
}
