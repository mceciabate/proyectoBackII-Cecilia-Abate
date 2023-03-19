package com.msbills.service;

import com.msbills.models.Bill;

import com.msbills.models.BillAndUser;
import com.msbills.models.User;
import com.msbills.repositories.BillRepository;
import com.msbills.repositories.IFeignUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

  private final BillRepository repository;

  private final IFeignUserRepository feignUserRepository;

  public List<Bill> getAllBill() {
    return repository.findAll();
  }

  public Bill saveBill(Bill bill) {
    return repository.save(bill);
  }

  public List<BillAndUser> findAllBillsByUsername(String username){
    User user = feignUserRepository.findByUsername(username);
    List<Bill> billList = repository.findByCustomerBill(username);
    System.out.println(user);
    List <BillAndUser> billAndUserList = new ArrayList<>();

    for(Bill b: billList){
      BillAndUser billAndUser = new BillAndUser(b.getBillingDate(),b.getProductBill(),b.getTotalPrice(), user.getUsername(),user.getFirstName(),user.getLastName(),user.getEmail());
      billAndUserList.add(billAndUser);
    }

    return billAndUserList;

  }

  //TODO ARREGLAR EL YML
  public List<Bill> findByCustomer(String customer) {
    ArrayList allBills = (ArrayList) repository.findAll();
    if (repository.findByCustomerBill(customer).isEmpty()){
      return null;
    } else
    return repository.findByCustomerBill(customer);
  }

}
