package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.models.BillAndUser;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bills/")
@RequiredArgsConstructor
public class BillController {

  private final BillService service;

  @GetMapping("/all")
  @PreAuthorize("hasRole('ROLE_admin')")
//  @PreAuthorize("hasAuthority('SCOPE_facturacion')") tambien se puede probar con este scope una vez asignado al user en el cliente interno
  public ResponseEntity<List<Bill>> getAll() {
    return ResponseEntity.ok().body(service.getAllBill());
  }


  @PostMapping()
  @PreAuthorize("hasRole('ROLE_admin')")
  //  @PreAuthorize("hasAuthority('SCOPE_facturacion')") tambien se puede probar con este scope una vez asignado al user en el cliente interno
  public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
    return ResponseEntity.ok().body(service.saveBill(bill));
  }

  @GetMapping("/detail/{username}")
  @PreAuthorize("hasRole('ROLE_admin')")
  //  @PreAuthorize("hasAuthority('SCOPE_facturacion')") tambien se puede probar con este scope una vez asignado al user en el cliente interno
  public List<BillAndUser> billsByUsername(@PathVariable ("username") String username){
    return service.findAllBillsByUsername(username);
  }


  @GetMapping("/findBy/{customer}")
  @PreAuthorize("hasRole('ROLE_admin')")
  public List<Bill> findByCustomer(@PathVariable String customer) {
    ArrayList<Bill> allBillsCostumer = (ArrayList<Bill>) service.findByCustomer(customer);
    return allBillsCostumer;

  }


}
