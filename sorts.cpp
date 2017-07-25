//冒泡排序
template<class T>
void bubbleSort(T* arr,int num) {
	for (int i = 0; i < num-1; i++) {
		for (int j = i; j < num - 1; j++) {
			if (arr[j] > arr[j+1]) {
				T temp = arr[j+1];
				arr[j+1] = arr[j];
				arr[j] = temp;
			}
		}
	}
}
//插入排序
template<class T>
void insertionSort(T* arr, int num) {
	for (int i = 1; i < num; i++) {
		int j;
		T temp = arr[i];
		for (j = i; j > 0 && arr[j-1] > temp; j--) {
			arr[j] = arr[j - 1];
		}
		arr[j] = temp;
	}
}
//选择排序
template<class T>
void  selectionSort(T* arr, int num) {
	for (int i = 0; i < num - 1; i++) {
		T minV = arr[i];
		int minIdx = i;
		for (int j = i+1; j < num; j++) {
			if (arr[j] < minV) {
				minV = arr[j];
				minIdx = j;
			}
		}
		if (minIdx != i) {
			T temp = arr[i];
			arr[i] = arr[minIdx];
			arr[minIdx] = temp;
		}
	}
}

template<class T>
int partiton(T* arr, int low, int high) {
	T temp = arr[low];
	while (low < high) {
		while (low < high&&arr[high] > temp)
			high--;
		arr[low] = arr[high];
		while (low < high&&arr[low] < temp)
			low++;
		arr[high] = arr[low];
	}
	arr[low] = temp;
	return low;
}
template<class T>
void qSort(T* arr, int low, int high) {
	if (low < high) {
		int pivotloc = partiton(arr, low, high);
		qSort(arr,low,pivotloc-1);
		qSort(arr, pivotloc + 1, high);
	}
}
//快速排序
template<class T>
void quickSort(T* arr, int num) {
	qSort(arr, 0, num - 1);
}

template<class T>
void heapAdjust(T* arr, int s, int m) {
	T temp = arr[s];
	for (int j = 2 * s + 1; j < m; j = 2 * j + 1) {
		if (arr[j] < arr[j + 1]) j++;
		if (temp > arr[j]) break;
		arr[s] = arr[j];
		s = j;
	}
	arr[s] = temp;
}
//堆排序
template<class T>
void heapSort(T* arr, int num) {
	//建成大顶堆，从最后一个非根节点往前
	for (int i = (num-1) / 2; i >= 0; i--) {
		heapAdjust(arr, i, num-1);
	}
	//排序，将堆顶值和最后一个值交换
	T temp;
	for (int j = num - 1; j > 0; j--) {
		temp = arr[0];
		arr[0] = arr[j];
		arr[j] = temp;
		heapAdjust(arr, 0, j - 1);
	}
}
//希尔排序
template<class T>
void shellSort(T* arr, int num) {
	for (int i = num / 2; i > 0; i /= 2) {//步长
		for (int j = 0; j < i; j++) {     //分组
			for (int k = i + j; k < num; k += i) {//插入排序
				T temp = arr[k];
				int l = k - i;
				while (l >= 0 && arr[l] > temp) {
					arr[l+i] = arr[l];
					l -= i;
				}
				arr[l+i] = temp;
			}
		}
	}
}
template<class T>
void merge(T* arr0, T* arr1, int i, int m, int n) {
	int idx = 0;
	int k = i, l = m + 1;
	while(k <= m && l <= n) {
		if (arr0[k] < arr0[l]) 
			arr1[idx++] = arr0[k++];
		else 
			arr1[idx++] = arr0[l++];
	}
	while (k <= m) arr1[idx++] = arr0[k++];
	while (l <= n) arr1[idx++] = arr0[l++];

	for (int k = 0; k < idx; k++)
		arr0[k + i] = arr1[k];
}

template<class T>
void mSort(T* arr0, T* arr1,int start, int end) {
	if (start == end) arr1[start] = arr0[start];
	else{
		int mid = (start + end) / 2;
		mSort(arr0, arr1, start, mid);
		mSort(arr0, arr1, mid + 1, end);
		merge(arr0, arr1, start, mid, end);
	}
}
//归并排序
template<class T>
void mergeSort(T *arr, int num) {
	int *p = new int[num];
	mSort(arr, p, 0, num - 1);
	delete[] p;
}