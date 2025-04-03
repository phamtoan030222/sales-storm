/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication8.until;

import java.util.List;

/**
 *
 * @author phamd
 */
public class PhanTrang<T> {
    private List<T> dataList;
    private int pageSize;
    private int currentPage;
    
    public PhanTrang(List<T> dataList, int pageSize){
        this.dataList = dataList;
        this.pageSize = pageSize;
        this.currentPage = 1;
    }
    
    public List<T> getCurrentPageData(){
        int fromIndex = (currentPage - 1)*pageSize;
        int toIndex = Math.min(fromIndex + pageSize, dataList.size());
        return dataList.subList(fromIndex, toIndex);
    }
    
    public void nextPage() {
        if (currentPage < getTotalPages()) {
            currentPage++;
        }
    }
    
    public void previousPage() {
        if (currentPage > 1) {
            currentPage--;
        }
    }
    
    public void firstPage() {
        currentPage = 1;
    }
    
    public void lastPage() {
        currentPage = getTotalPages();
    }
    
    public int getTotalPages() {
        return (int) Math.ceil((double) dataList.size() / pageSize);
    }
    
    public int getCurrentPage() {
        return currentPage;
    }

}
