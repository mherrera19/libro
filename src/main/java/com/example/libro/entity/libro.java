/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libro.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author BReyna
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libro")
public class libro implements Serializable{
    private static final long serialVersionUID = 3754851399214200439L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlibro")
	private int id;
	private String nom_libro;	
	
	@ManyToOne
	@JoinColumn(name="idautor", nullable = false)
	private Autor autor;
        
        @ManyToOne
	@JoinColumn(name="ideditorial", nullable = false)
	private Editorial editorial;
}
