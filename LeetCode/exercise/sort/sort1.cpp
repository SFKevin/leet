#include "../sort/sort1.h"

Sort1::Sort1() {}
Sort1::~Sort1() {}

void Sort1::SelectSort(vector<int> &a)
{
    int len = a.size();
    int temp;
    int flag;
    for (int i = 0; i < len; i++) {
        temp = a[i];
        flag = i;
        for (int j = i + 1; j < len; j++) {
            if (a[j] < temp) {
                temp = a[j];
                flag = j;
            }
        }
        if (flag != i) {
            a[flag] = a[i];
            a[i] = temp;
        }
    }
}

void Sort1::InsertSort(vector<int> &a)
{
    int len = a.size();
    if (len <= 1) {
        return;
    }
    int temp;
    for (int i = 1; i < len; i++) {
        int j = i;
        temp = a[i];
        while (j >= 1 && a[j - 1] > temp) {
            a[j] = a[j - 1];
            j--;
        }
        a[j] = temp;

    }
}

void Sort1::ShellSort(vector<int> &a)
{
    int len = a.size();
    for (int h = len/2; h > 0; h = h /2) {
        for (int i = h; i < len; i++) {
            int j = i;
            int temp = a[i];
            while(j >= h && a[j - h] > temp) {
                a[j] = a[j - h];
                j = j -h;
            }
            a[j] = temp;
        }
    }
}

void Sort1::QuickSort(vector<int>& a, int low, int high)
{
    if (low >= high) {
        return;
    }
    int i = low;
    int j = high;
    int index = a[i];
    while (i < j) {
        while (i < j && a[j] > index) {
            j--;
        }
        if (i < j) {
            a[i++] = a[j]; 
        }
        while(i < j && a[i] < index) {
            i++;
        }
        if(i < j) {
            a[j--] = a[i];
        }
    }
    a[i] = index;
    QuickSort(a, low, i - 1);
    QuickSort(a, i + 1, high);
}

void Sort1::Merge(vector<int>& a, int p, int q, int r)
{
    int n1 = q - p + 1;
    int n2 = r - q;
    int* L = new int[n1];
    int* R = new int[n2];
    int i, j, k;
    for (i = 0, k= p; i < n1; i++, k++) {
        L[i] = a[k];
    }

    for (j = 0, k = q + 1; j < n2; j++, k++) {
        R[j] = a[k];
    }

    for (i = 0, j = 0, k = p; i < n1 && j < n2; k++) {
        if (L[i] < R[j]) {
            a[k] = L[i];
            i++;
        }else {
            a[k] = R[j];
            j++;
        }
    }

    while (i < n1) {
        a[k] = L[i];
        k++;
        i++;
    }

    while(j < n2) {
        a[k] = R[j];
        k++;
        j++;
    }

    delete[] L;
    delete[] R;
    L = NULL;
    R = NULL;
}

void Sort1::MergeSort(vector<int>& a, int p, int r)
{
    if (p < r) {
        int q = (p + r) / 2;
        MergeSort(a, p, q);
        MergeSort(a, q + 1, r);
        Merge(a, p, q, r);

    } 
}