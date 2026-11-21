package com.bahmanexample.lezzat.Repository;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bahmanexample.lezzat.Activity.ListFoodActivity;
import com.bahmanexample.lezzat.Activity.MainActivity;
import com.bahmanexample.lezzat.Domain.Category;
import com.bahmanexample.lezzat.Domain.Foods;
import com.bahmanexample.lezzat.Domain.Location;
import com.bahmanexample.lezzat.Domain.Price;
import com.bahmanexample.lezzat.Domain.Time;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Repository {
    private static final String TAG = "Repository";
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    MutableLiveData<ArrayList<Location>> MyLocationLivedata;
    MutableLiveData<ArrayList<Time>> MyTimeLiveData;
    MutableLiveData<ArrayList<Price>> MyPriceLiveData;
    MutableLiveData<ArrayList<Foods>> MyFoodsLiveData;
    MutableLiveData<ArrayList<Category>> MyCategoryLiveData;
    MutableLiveData<ArrayList<Foods>> MyListsLiveData;







    public Repository() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        mAuth = FirebaseAuth.getInstance();
        MyLocationLivedata = new MutableLiveData<ArrayList<Location>>();
        MyTimeLiveData = new MutableLiveData<ArrayList<Time>>();
        MyPriceLiveData = new MutableLiveData<ArrayList<Price>>();
        MyFoodsLiveData = new MutableLiveData<ArrayList<Foods>>();
        MyCategoryLiveData = new MutableLiveData<ArrayList<Category>>();
        MyListsLiveData = new MutableLiveData<ArrayList<Foods>>();
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        MutableLiveData<FirebaseUser> currentUser = new MutableLiveData<>();
        currentUser.setValue(mAuth.getCurrentUser());
        return currentUser;
    }
    // Implement this method if required for sign-up
    public void firebaseSignupWithEmailAndPassword(Context context, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.i(TAG, "onComplete: ");

                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    public void signInwithemailpassword(Context context, String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i(TAG, "Sign up successful");
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }
    public void signOut(final Context context) {
        mAuth.signOut();
    }

    //Location Data

    public MutableLiveData<ArrayList<Location>> getMyLocationLivedata(Context context) {
        DatabaseReference ref = database.getReference("Location");
        ArrayList<Location> list = new ArrayList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for(DataSnapshot issue: snapshot.getChildren()){
                        Location currentLocation = issue.getValue(Location.class) ;
                        list.add(currentLocation);
                    }
                    MyLocationLivedata.postValue(list);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return MyLocationLivedata;
    }
    //Time Live Data
    public MutableLiveData<ArrayList<Time>> getMyTimeLivedata(Context context) {
        DatabaseReference ref = database.getReference("Time");
        ArrayList<Time> timeList = new ArrayList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for(DataSnapshot issue: snapshot.getChildren()){
                        Time currentTime = issue.getValue(Time.class) ;
                        timeList.add(currentTime);
                    }
                    MyTimeLiveData.postValue(timeList);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return MyTimeLiveData;
    }
    //Price Live data
    public MutableLiveData<ArrayList<Price>> getMyPriceLivedata(Context context) {
        DatabaseReference ref = database.getReference("Price");
        ArrayList<Price> priceList = new ArrayList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for(DataSnapshot issue: snapshot.getChildren()){
                        Price currentPrice = issue.getValue(Price.class) ;
                        priceList.add(currentPrice);
                    }
                    MyPriceLiveData.postValue(priceList);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return MyPriceLiveData;
    }

    public MutableLiveData<ArrayList<Foods>> getMyFoodsLiveData(Context context) {
        DatabaseReference ref = database.getReference("Foods");
        ArrayList<Foods> foodslist = new ArrayList<>();
        Query query = ref.orderByChild("BestFood").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Foods currentFood = issue.getValue(Foods.class);
                        foodslist.add(currentFood);
                    }
                    MyFoodsLiveData.postValue(foodslist);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return MyFoodsLiveData;
    }

    public MutableLiveData<ArrayList<Category>> getMyCategoryLiveData(Context context) {
        DatabaseReference ref = database.getReference("Category");
        ArrayList<Category> categoryList = new ArrayList<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Category currentFood = issue.getValue(Category.class);
                        categoryList.add(currentFood);
                    }
                    MyCategoryLiveData.postValue(categoryList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return MyCategoryLiveData;
    }

    public MutableLiveData<ArrayList<Foods>> getMyListsLiveData(Context context,boolean isSearch, String searchText, int categoryId ) {
        DatabaseReference myRef = database.getReference("Foods");
        ArrayList<Foods> list = new ArrayList<>();

        Query query;
        if(isSearch) {
            query = myRef.orderByChild("Title").startAt(searchText).endAt(searchText+'\uf8ff');
        }else {
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Foods myfoods =  issue.getValue(Foods.class);
                        list.add(myfoods);
                    }
                    MyListsLiveData.postValue(list);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return MyListsLiveData;
    }
}
