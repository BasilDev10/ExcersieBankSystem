package com.example.exercisecustomercontroller.Controller;

import com.example.exercisecustomercontroller.ApiResponse.ApiResponse;
import com.example.exercisecustomercontroller.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomer(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return new ApiResponse("Customer added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer){
        if(index > customers.size()-1)return new ApiResponse("Customer not found");

        customers.set(index, customer);

        return new ApiResponse("Customer updated successfully");

    }
    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index, @PathVariable int amount){
        Customer customer = customers.get(index);
        if(customer == null)return new ApiResponse("Customer not found");


        if (amount <= 0)return new ApiResponse("you can't deposit negative amount or zero");

        customer.setBalance(customer.getBalance()+amount);

        return new ApiResponse("Customer deposit successfully");

    }

    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw(@PathVariable int index, @PathVariable int amount){
        Customer customer = customers.get(index);
        if(customer == null)return new ApiResponse("Customer not found");


        if (amount <= 0)return new ApiResponse("you can't withdraw negative amount or zero");

        if (amount > customer.getBalance()) return new ApiResponse("Error: Amount is greater than your balance");

        customer.setBalance(customer.getBalance()-amount);

        return new ApiResponse("Customer withdraw successfully");

    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        if(index > customers.size()-1)return new ApiResponse("Customer not found");
        customers.remove(index);
        return new ApiResponse("Customer deleted successfully");
    }

}
