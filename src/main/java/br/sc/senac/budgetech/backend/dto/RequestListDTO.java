package br.sc.senac.budgetech.backend.dto;

import br.sc.senac.budgetech.backend.enumeration.Payment;
import br.sc.senac.budgetech.backend.enumeration.Status;

import java.time.LocalDate;

public record RequestListDTO(Long id, double price, Status status, Payment payment, LocalDate initialDate, LocalDate finalDate) {}