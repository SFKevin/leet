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

void Sort::insertSort(vector<int>& a){
    int len = a.size();
    if(len <= 1){
        return;
    }

    for(int i = 1; i < len; i++){
        int j = i;
        int temp = a[i];
        while(j >= 1 && a[j-1] > temp){
            a[j] = a[j-1];
            j--;
        }
        a[j] = temp;
    }
}

void Sort::bubbleSort(vector<int>& a){
    int len = a.size();
    if(len <=1){
        return;
    }

    int temp;
    for(int i = 0; i < len; i++){
        for(int j = len - 1; j > i; j--){
            if(a[j] < a[j-1]){
                temp = a[j-1];
                a[j-1] = a[j];
                a[j] = temp;
            }
        }
    }
}

void Sort::quickSort(vector<int>& a, int low, int high){
    if(low > high){
        return;
    }

    int i = low;
    int j = high;
    int index = a[i];
    while(i < j){
        while(i < j && index < a[j]){
            j--;
        }
        if(i < j){
            a[i++] = a[j];
        }
        while(i < j && index > a[i]){
            i++;
        }
        if(i < j){
            a[j--] = a[i];
        }
    }
    a[i] = index;
    quickSort(a, low, i-1);
    quickSort(a, i+1, high);
}

void Sort::shellSort(vector<int>& a){
    int len = a.size();
    int temp;
    for(int h = len / 2; h > 0; h = h/2){
        for(int i = h; i < len; i++){
            int j = i;
            temp = a[i];
            while(j >= h && a[j-h] > a[i]){
                a[j] = a[j-h];
                j -= h;
            }
            a[j] = temp;
        }
    }
}

void Sort::merge(vector<int>& a, int p, int q, int r){
    int n1 = q - p + 1;
    int n2 = r - q;
    int *L = new int[n1];
    int *R = new int[n2];
    int i , j, k;
    for(int i = 0, k = p; i < n1; i++){
        L[i] = a[k++];
    }
    for(int j = 0, k = q + 1; j < n2; j++){
        R[j] = a[k++];
    }

    for(i = 0, j = 0, k = p; i < n1 && j < n2; k++){
        if(L[i] < R[j]){
            a[k] = L[i++];
        }
        else{
            a[k] = R[j++];
        }
    }

    while(i < n1){
        a[k++] = L[i++];
    }
    while(j < n2){
        a[k++] = R[j++];
    }

    delete[] L; 
    L = NULL;
    delete[] R;
    R = NULL;
}

void Sort::mergeSort(vector<int>& a, int p, int r){
    if(p < r){
        int q = (p + r)/2;
        mergeSort(a, p, q);
        mergeSort(a, q + 1, r);
        merge(a, p, q, r);
    }
}

int Sort::leftChild(int i){
    return 2 * i + 1;
}

void Sort::adjustHeap(vector<int>& a, int i, int n){
    int father;
    int child = 0;
    for(father = a[i]; leftChild(i) < n; i = child){
        child = leftChild(i);
        if(child < n && a[child] < a[child + 1]){
            child++;
        }
        if(father < a[child]){
            a[i] = a[child];
        }
        else{
            break;
        }
    }
    a[i] = father;
}

void Sort::swap(vector<int>& a, int index1, int index2){
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
}

void Sort::heapSort(vector<int>& a){
    int len = a.size();
    for(int i = len /2 - 1; i >= 0; i--){
        adjustHeap(a, i, len);
    }
    for(int i = len - 1; i >=0; i--){
        swap(a, 0, i);
        adjustHeap(a, 0, i - 1);
    }

}