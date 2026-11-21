package com.bahmanexample.lezzat.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bahmanexample.lezzat.Domain.Category;
import com.bahmanexample.lezzat.Domain.Foods;
import com.bahmanexample.lezzat.Domain.Location;
import com.bahmanexample.lezzat.Domain.Price;
import com.bahmanexample.lezzat.Domain.Time;
import com.bahmanexample.lezzat.Repository.Repository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MyViewModel extends AndroidViewModel {
    Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }
    public LiveData<FirebaseUser> getCurrentUser(){
        return repository.getCurrentUser();
    }
    public void firebaseSignupWithEmailAndPassword( String email, String passord){
        Context context = this.getApplication();
        repository.firebaseSignupWithEmailAndPassword(context, email, passord);
    }
    public void signInwithemailpassword(String email, String password) {
        Context context = this.getApplication();
        repository.signInwithemailpassword(context, email, password);
    }
    public void signOut() {
        Context context = this.getApplication();
        repository.signOut(context);
    }

    //Location ViewModel
    public MutableLiveData<ArrayList<Location>> getMyLocationLivedata(){
        Context context = this.getApplication();
        return repository.getMyLocationLivedata(context);
    }
    //Time ViewModel
    public MutableLiveData<ArrayList<Time>> getMyTimeLivedata(){
        Context context = this.getApplication();
        return repository.getMyTimeLivedata(context);
    }
    public MutableLiveData<ArrayList<Price>> getMyPriceLivedata(){
        Context context = this.getApplication();
        return repository.getMyPriceLivedata(context);
    }
    public MutableLiveData<ArrayList<Foods>> getMyFoodsLivedata(){
        Context context = this.getApplication();
        return repository.getMyFoodsLiveData(context);
    }
    public MutableLiveData<ArrayList<Category>> getMyCategoryLivedata(){
        Context context = this.getApplication();
        return repository.getMyCategoryLiveData(context);
    }
    public MutableLiveData<ArrayList<Foods>> getMyListsLiveData(boolean isSearch, String searchText, int categoryId ){
        Context context = this.getApplication();
        return repository.getMyListsLiveData(context,isSearch,searchText,categoryId);
    }
}
