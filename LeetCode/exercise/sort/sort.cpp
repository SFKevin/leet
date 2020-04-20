#include "../sort/sort.h"

Sort::Sort(){}
Sort::~Sort(){}

void Sort::selectSort(std::vector<int> &a){
    int len = a.size();
    int temp = 0;
    int flag = 0;
    for(int i = 0; i < len; i++)
    {
        temp = a[i];
        flag = i;
        for(int j = i + 1; j < len; j++)
        {
            if(a[j] < temp)
            {
                temp = a[j];
                flag = j;
            }
        }
        if(flag != i)
        {
            a[flag] = a[i];
            a[i]    = temp;
        }
    }
}