package com.example.paypal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transaction")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "transactionId", updatable = false, nullable = false)
  private long transactionId;

  @Column(name = "type")
  private String type;

  @Column(name = "value")
  private double value;

  @Column(name = "transactOn")
  @Temporal(TemporalType.TIMESTAMP)
  private Date transactOn;

  @ManyToOne
  @JoinColumn(name = "userId")
  @JsonBackReference
  private User user;
}
