package com.mercury.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="all_user", uniqueConstraints={
		@UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" })
})
public class User{
	// User fields
	private int id;
	private String username;
	private String email;
	private String phone;
	private String firstName;
	private String lastName;
	private String password;
	
	// Address fields
	private String zip_code;
	private String street;
	private String city;
	private String state;
	
	private Set<Card> cards;
	private Set<Order> orders;
	
	public User() {
		this.cards = new HashSet<Card>();
		this.orders = new HashSet<Order>();
	}
	public User(String username, String email, String phone, 
			String firstName, String lastName, String password,
			char type, String zip_code, String street, String city,
			String state) {
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		
		this.zip_code = zip_code;
		this.street = street;
		this.city = city;
		this.state = state;
		
		this.cards = new HashSet<Card>();
		this.orders = new HashSet<Order>();
	}
	
	@Override
	public String toString() {
		return "ID: " + this.id + "\tUsername: " + this.username + "\tEmail: " +
				this.email + "\tPhone: " + this.phone + "\tFName: " +
				this.firstName + "\tLName: " + this.lastName + "\tPassword: " + 
				this.password + "\n\tType: " +
				"\tZipCode: " + this.zip_code + "\tStreet: " + this.street +
				"\tCity: " + this.city + "\tState: " + this.state;
	}
	
	@Id
	@GenericGenerator(name="kaugen", strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="username", unique=true, nullable=false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="email", unique=true, nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="password", nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="zip_code")
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	
	@Column(name="street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@Column(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN})
	public Set<Card> getCards() {
		return cards;
	}
	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	public void removeCard(Card card) {
		this.cards.remove(card);
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	public void removeOrder(Order order) {
		this.orders.remove(order);
	}
}
