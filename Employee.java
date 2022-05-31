import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;

public class Employee {
	static Employee e = new Employee();
	static Month month = Month.from(LocalDate.now());
	String[] namesOfEmployees = new String[10];
	byte[] ageOfEmployees = new byte[10];
	String[] designationOfEmployees = new String[10];
	int[] salaryOfEmployees = new int[10];
	String[] employeeId = new String[10];
	String[] months = new String[12];
	int countMonth = 1;
	int flow = 0;
	int first = 0;
	String line;
	String designation;
	int detailcount;
	byte option;
	byte age;
	byte index;
	byte createIndex = 0;
	int detailindex;

//choices method
	void choices() {
		boolean flagOption = true;
		e.fileWrite();
		while (flagOption) {
			try {
				System.out.println("\nChoose from the below choices: ");
				System.out.println(
						"1) Create Employee \n2) Display Employee \n3) Raise Employee Salary \n4) Delete Employee \n5) Exit");
				System.out.print("Enter The option from the choices (1-5): ");
				Scanner sc = new Scanner(System.in);
				option = sc.nextByte();
				flagOption = false;
			} catch (Exception e) {
				System.out.println("Invalid Character entered");
				flagOption = true;
			}
		}
		switch (option) {
		case 1:
			e.create();
			break;
		case 2:
			e.display();
			break;
		case 3:
			e.raiseSalary();
			break;
		case 4:
			e.deleteEmployee();
			break;
		case 5:
			e.exit();
			break;
		default:
			System.out.println("Invalid option");
			choices();
		}

	}

	public int[] removeTheElement(int[] arr, int index1) {

		if (arr == null || index1 < 0 || index1 >= arr.length) {

			return arr;
		}

		int[] anotherArray = new int[arr.length - 1];

		for (int i = 0, k = 0; i < arr.length; i++) {

			if (i == index1) {
				continue;
			}
			anotherArray[k++] = arr[i];
		}

		return anotherArray;
	}

	public String[] removeTheElement(String[] arr, int index2) {

		if (arr == null || index2 < 0 || index2 >= arr.length) {

			return arr;
		}

		String[] anotherArray = new String[arr.length - 1];

		for (int i = 0, k = 0; i < arr.length; i++) {

			if (i == index2) {
				continue;
			}
			anotherArray[k++] = arr[i];
		}

		return anotherArray;
	}

	public byte[] removeTheElement(byte[] arr, int index3) {

		if (arr == null || index3 < 0 || index3 >= arr.length) {

			return arr;
		}

		byte[] anotherArray = new byte[arr.length - 1];

		for (int i = 0, k = 0; i < arr.length; i++) {

			if (i == index3) {
				continue;
			}
			anotherArray[k++] = arr[i];
		}

		return anotherArray;
	}

//checks for the spaces in the name
	boolean spaces(String name) {
		int count = 0;

		for (int i = 0; i < name.length() - 2; i++) {
			if (("" + name.charAt(i) + name.charAt(i + 1) + name.charAt(i + 2)).equals("   ")) {
				count = count + 1;
			}
		}

		if (count >= 1) {
			return false;
		} else {
			return true;
		}

	}

//checks for the number of words in the name
	boolean words(String name) {
		int noOfWords = 0;
		for (int j = 0; j < name.length() - 1; j++) {
			if (name.charAt(j) == ' ' & name.charAt(j + 1) != ' ') {
				noOfWords = noOfWords + 1;
			}
		}
		if (noOfWords >= 3) {
			return false;
		} else {
			return true;
		}
	}

//checks for the invalid characters entered at the beginning of the name
	boolean numbers(String name) {
		if (name.length() >= 1) {
			char c = name.charAt(0);
			int a = (int) c;
			if (!((a >= 65 && a <= 90) || (a >= 97 && a <= 122))) {
				return false;
			}
		} else if (name.length() == 0) {
			return false;
		}
		return true;
	}

//checks for any invalid designation entered
	boolean designationflag(String designation) {
		String test;
		if ((designation.toLowerCase()).charAt(0) == 'p') {
			test = designation.toLowerCase();
			boolean flag = true;
			for (int i = 0; i < test.length() && flag; i++) {
				if (test.charAt(i) == ("programmer").charAt(i)) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
			return flag;
		} else if ((designation.toLowerCase()).charAt(0) == 'm') {
			test = designation.toLowerCase();
			boolean flag = true;
			for (int i = 0; i < test.length() && flag; i++) {
				if (test.charAt(i) == ("manager").charAt(i)) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
			return flag;

		} else if ((designation.toLowerCase()).charAt(0) == 't') {
			test = designation.toLowerCase();
			boolean flag = true;
			for (int i = 0; i < test.length() && flag; i++) {
				if (test.charAt(i) == ("tester").charAt(i)) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
			return flag;

		} else {
			return false;
		}
	}

//checks if the month already exists
	boolean monthFlag(String monthName) {
		for (int i = 0; i < months.length && months[i] != null; i++) {
			if (monthName.equalsIgnoreCase(months[i])) {
				return false;
			}
		}
		return true;

	}

//Read from file
	void file() {
		int i = 0;
		int maxval;
		try {
			File myObj = new File("F:\\Project\\Data\\data.txt");
			if (!(myObj.createNewFile())) {

				try {
					line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
							.get(0);
					detailcount = Integer.parseInt(line);
					line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
							.get(1);
					countMonth = Integer.parseInt(line);
				} catch (Exception e) {

				}

				createIndex = (byte) detailcount;
				maxval = 5 + detailcount;
				detailindex = 5;

				i = 0;
				while (detailindex < (maxval)) {
					try {
						line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
								.get(detailindex);
						namesOfEmployees[i] = line;

						detailindex = detailindex + 1;
						i = i + 1;
					} catch (Exception e) {

					}
				}

				maxval = detailindex + detailcount + 3;
				detailindex = detailindex + 3;
				i = 0;
				while (detailindex < (maxval)) {
					try {
						line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
								.get(detailindex);
						ageOfEmployees[i] = (byte) Integer.parseInt(line);

						detailindex = detailindex + 1;
						i = i + 1;
					} catch (Exception e) {

					}
				}

				maxval = detailindex + detailcount + 3;
				detailindex = detailindex + 3;
				i = 0;
				while (detailindex < (maxval)) {
					try {
						line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
								.get(detailindex);
						designationOfEmployees[i] = line;

						detailindex = detailindex + 1;
						i = i + 1;
					} catch (Exception e) {

					}
				}

				maxval = detailindex + detailcount + 3;
				detailindex = detailindex + 3;
				i = 0;
				while (detailindex < (maxval)) {
					try {
						line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
								.get(detailindex);
						salaryOfEmployees[i] = Integer.parseInt(line);
						detailindex = detailindex + 1;
						i = i + 1;
					} catch (Exception e) {

					}
				}

				maxval = detailindex + detailcount + 3;
				detailindex = detailindex + 3;
				i = 0;
				while (detailindex < (maxval)) {
					try {
						line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
								.get(detailindex);
						employeeId[i] = line;

						detailindex = detailindex + 1;
						i = i + 1;
					} catch (Exception e) {

					}
				}
				Path file = Paths.get("F:\\Project\\Data\\data.txt");
				int count = (int) Files.lines(file).count();
				maxval = count;
				detailindex = detailindex + 3;
				i = 0;
				while (detailindex < (maxval)) {
					try {
						line = Files.readAllLines(Paths.get("F:\\Project\\Data\\data.txt"))
								.get(detailindex);
						months[i] = line;

						detailindex = detailindex + 1;
						i = i + 1;

					} catch (Exception e) {
						break;

					}
				}

			}
		} catch (Exception e) {
			System.out.println("File already created");
		}

	}

//write to file
	void fileWrite() {
		int count = 0;
		String s = "------------------------------";
		for (int i = 0; i < employeeId.length && namesOfEmployees[i] != null; i++) {
			count = count + 1;
		}

		try {
			FileWriter myObj = new FileWriter("F:\\Project\\Data\\data.txt");
			myObj.write(Integer.toString(count));
			myObj.write("\n");
			myObj.write(Integer.toString(countMonth));
			myObj.write("\n");
			myObj.write(s);
			myObj.write("\n");
			myObj.write("Employee Names");
			myObj.write("\n");
			myObj.write(s);
			myObj.write("\n");

			for (int i = 0; i < namesOfEmployees.length && namesOfEmployees[i] != null; i++) {
				myObj.write(namesOfEmployees[i]);
				myObj.write("\n");
			}
			myObj.write(s);
			myObj.write("\n");
			myObj.write("Employee ages");
			myObj.write("\n");
			myObj.write(s);
			myObj.write("\n");

			for (int i = 0; i < ageOfEmployees.length && ageOfEmployees[i] != 0; i++) {
				myObj.write(Integer.toString(ageOfEmployees[i]));
				myObj.write("\n");
			}
			myObj.write(s);
			myObj.write("\n");
			myObj.write("Employee desinations");
			myObj.write("\n");
			myObj.write(s);
			myObj.write("\n");

			for (int i = 0; i < designationOfEmployees.length && namesOfEmployees[i] != null; i++) {
				myObj.write(designationOfEmployees[i]);
				myObj.write("\n");
			}
			myObj.write(s);
			myObj.write("\n");
			myObj.write("Employee salaries");
			myObj.write("\n");
			myObj.write(s);
			myObj.write("\n");

			for (int i = 0; i < salaryOfEmployees.length && namesOfEmployees[i] != null; i++) {
				myObj.write(Integer.toString(salaryOfEmployees[i]));
				myObj.write("\n");
			}
			myObj.write(s);
			myObj.write("\n");
			myObj.write("Employee ID's");
			myObj.write("\n");
			myObj.write(s);
			myObj.write("\n");

			for (int i = 0; i < employeeId.length && namesOfEmployees[i] != null; i++) {
				myObj.write(employeeId[i]);
				myObj.write("\n");

			}
			myObj.write(s);
			myObj.write("\n");
			myObj.write("Months of Employees");
			myObj.write("\n");
			myObj.write(s);
			myObj.write("\n");

			for (int i = 0; i < months.length && months[i] != null; i++) {

				myObj.write(months[i]);
				myObj.write("\n");

			}

			myObj.close();

		} catch (Exception e) {
			System.out.println("File Writing error " + (e));
		}

	}

//method for creation of employee
	void create() {
		System.out.println("\n");
		boolean flag = true;
		Scanner s = new Scanner(System.in);
		String name;
		boolean flagSpace;
		boolean flagWords;
		boolean flagNumbers;
		boolean ageflag;
		while (flag && createIndex < 10) {
			System.out.print("Enter name: ");
			name = (s.nextLine()).trim();
			flagSpace = spaces(name);
			flagWords = words(name);
			flagNumbers = numbers(name);
			namesOfEmployees[createIndex] = name;
			while (!(flagSpace && flagWords && flagNumbers)) {
				System.out.println("Invalid name");
				System.out.print("Enter name: ");
				name = (s.nextLine()).trim();
				flagSpace = spaces(name);
				flagWords = words(name);
				flagNumbers = numbers(name);
				namesOfEmployees[createIndex] = name;
			}
			ageflag = true;
			while (ageflag) {

				try {
					System.out.print("Enter age: ");
					age = (byte) Integer.parseInt(s.nextLine());
					if (!(age < 18 || age > 60)) {
						ageOfEmployees[createIndex] = age;
						ageflag = false;
					} else {
						System.out.println("Age not applicable");
						ageflag = true;
					}
				} catch (Exception e) {
					System.out.println("Invalid age");
					ageflag = true;
				}
			}

			boolean flagDesignation = true;
			while (flagDesignation) {
				System.out.println("Enter Designation from \n1. Programmer \n2. Manager \n3. Tester");
				System.out.println("Enter from the given choices");
				try {
					designation = s.next();
				} catch (Exception ex) {
					flagDesignation = true;
					System.out.println("Enter valid Designation");
				}

				if ((designation.charAt(0) == 'p' || designation.charAt(0) == 'P') && designationflag(designation)) {
					designationOfEmployees[createIndex] = "Programmer";
					salaryOfEmployees[createIndex] = 20000;
					if (countMonth <= 9) {
						String val;
						if (first == 0) {
							months[flow] = month.name();
							val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
							flow = flow + 1;
							countMonth = countMonth + 1;
							first = first + 1;
						} else {
							if (monthFlag(month.name())) {
								months[flow] = month.name();
								countMonth = 1;
								val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
								flow = flow + 1;
							} else {
								val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
								countMonth = countMonth + 1;
							}
						}
						employeeId[createIndex] = val;
					} else {
						String val;
						if (first == 0) {
							months[flow] = month.name();
							val = "ACS" + (month.name()).substring(0, 3) + countMonth;
							flow = flow + 1;
							countMonth = countMonth + 1;
							first = first + 1;
						} else {
							if (monthFlag(month.name())) {
								months[flow] = month.name();
								countMonth = 1;
								val = "ACS" + (month.name()).substring(0, 3) + countMonth;
								flow = flow + 1;
							} else {
								val = "ACS" + (month.name()).substring(0, 3) + countMonth;
								countMonth = countMonth + 1;
							}
						}
						employeeId[createIndex] = val;
					}
					flagDesignation = false;
				} else if ((designation.charAt(0) == 'm' || designation.charAt(0) == 'M')
						&& designationflag(designation)) {
					designationOfEmployees[createIndex] = "Manager";
					salaryOfEmployees[createIndex] = 25000;
					if (countMonth <= 9) {
						String val;
						if (first == 0) {
							months[flow] = month.name();
							val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
							flow = flow + 1;
							countMonth = countMonth + 1;
							first = first + 1;
						} else {
							if (monthFlag(month.name())) {
								months[flow] = month.name();
								countMonth = 1;
								val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
								flow = flow + 1;
							} else {
								val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
								countMonth = countMonth + 1;
							}
						}
						employeeId[createIndex] = val;
					} else {
						String val;
						if (first == 0) {
							months[flow] = month.name();
							val = "ACS" + (month.name()).substring(0, 3) + countMonth;
							flow = flow + 1;
							countMonth = countMonth + 1;
							first = first + 1;
						} else {
							if (monthFlag(month.name())) {
								months[flow] = month.name();
								countMonth = 1;
								val = "ACS" + (month.name()).substring(0, 3) + countMonth;
								flow = flow + 1;
							} else {
								val = "ACS" + (month.name()).substring(0, 3) + countMonth;
								countMonth = countMonth + 1;
							}
						}
						employeeId[createIndex] = val;
					}
					flagDesignation = false;
				} else if ((designation.charAt(0) == 't' || designation.charAt(0) == 'T')
						&& designationflag(designation)) {
					designationOfEmployees[createIndex] = "Tester";
					salaryOfEmployees[createIndex] = 15000;
					if (countMonth <= 9) {
						String val;
						if (first == 0) {
							months[flow] = month.name();
							val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
							flow = flow + 1;
							countMonth = countMonth + 1;
							first = first + 1;
						} else {
							if (monthFlag(month.name())) {
								months[flow] = month.name();
								countMonth = 1;
								val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
								flow = flow + 1;
							} else {
								val = "ACS" + (month.name()).substring(0, 3) + "0" + countMonth;
								countMonth = countMonth + 1;
							}
						}
						employeeId[createIndex] = val;
					} else {
						String val;
						if (first == 0) {
							months[flow] = month.name();
							val = "ACS" + (month.name()).substring(0, 3) + countMonth;
							flow = flow + 1;
							countMonth = countMonth + 1;
							first = first + 1;
						} else {
							if (monthFlag(month.name())) {
								months[flow] = month.name();
								countMonth = 1;
								val = "ACS" + (month.name()).substring(0, 3) + countMonth;
								flow = flow + 1;
							} else {
								val = "ACS" + (month.name()).substring(0, 3) + countMonth;
								countMonth = countMonth + 1;
							}
						}
						employeeId[createIndex] = val;
					}
					flagDesignation = false;
				} else {
					System.out.println("Invalid Designation");
					flagDesignation = true;
				}
			}
			createIndex++;
			System.out.print("Do you want to continue.Yes/No: ");
			String choice = s.next();
			String none = s.nextLine();
			if (choice.equalsIgnoreCase("Yes")) {
				flag = true;
				System.out.println("\n");
			} else {
				flag = false;
				break;
			}

		}
		e.choices();
	}

//method to display the employee details
	void display() {
		System.out.println("\n");
		System.out.println("\nDetails of the Employees are: ");
		for (int k = 0; k < namesOfEmployees.length && namesOfEmployees[k] != null; k++) {
			System.out.println(employeeId[k] + " " + namesOfEmployees[k] + " " + ageOfEmployees[k] + " "
					+ designationOfEmployees[k] + " " + salaryOfEmployees[k]);
		}
		e.choices();
	}

//method to raise the salary of the employees
	void raiseSalary() {
		System.out.println("\n");
		boolean foundFlag = true;
		boolean foundFlagAge = true;
		Scanner sc = new Scanner(System.in);
		while (foundFlag) {
			System.out.print("Enter Name: ");
			String tempName = sc.nextLine();
			for (int k = 0; k < salaryOfEmployees.length && namesOfEmployees[k] != null; k++) {
				if ((namesOfEmployees[k]).equals(tempName)) {
					index = (byte) k;
					foundFlag = false;
					break;
				}
			}
			if (foundFlag) {
				System.out.println("Name Not found\n");
			}
		}
		while (foundFlagAge) {
			try {
				System.out.print("Enter age: ");
				int tempAge = Integer.parseInt(sc.next());
				if ((ageOfEmployees[index]) == (tempAge)) {
					foundFlagAge = false;
				}

				if (foundFlagAge) {
					System.out.println("Age Not found\n");
				}
			} catch (Exception e) {
				System.out.println("Invalid age\n");
				foundFlagAge = true;
			}
		}
		int increment;
		boolean incrementflag = true;
		while (incrementflag) {

			try {
				System.out.print("Enter Increment: ");
				increment = Integer.parseInt(sc.next());
				if (!(increment > 10 || increment <= 0)) {
					int result = salaryOfEmployees[index] + (salaryOfEmployees[index]) * increment / 100;
					salaryOfEmployees[index] = result;
					incrementflag = false;
				} else {
					System.out.println("Enter valid Increment");
					incrementflag = true;
				}
			} catch (Exception e) {
				System.out.println("Invalid Increment");
				incrementflag = true;
			}
		}
		e.choices();
	}

//method to delete the employee
	void deleteEmployee() {
		System.out.println("\n");
		Scanner sc = new Scanner(System.in);
		boolean delFlag = true;

		while (delFlag) {
			System.out.print("\nEnter ID: ");
			String target = sc.nextLine();
			for (int k = 0; k < employeeId.length && namesOfEmployees[k] != null; k++) {
				if ((employeeId[k]).equals(target)) {
					delFlag = false;
					employeeId = e.removeTheElement(employeeId, k);
					namesOfEmployees = e.removeTheElement(namesOfEmployees, k);
					ageOfEmployees = e.removeTheElement(ageOfEmployees, k);
					designationOfEmployees = e.removeTheElement(designationOfEmployees, k);
					salaryOfEmployees = e.removeTheElement(salaryOfEmployees, k);
					System.out.println("Employee Deleted");
					break;
				}
			}
			if (delFlag) {
				System.out.println("Incorrect ID");
			}

		}

		e.choices();
	}

//method to exit from the page
	void exit() {
		System.out.println("\n");
		System.out.println("---------- Thank You! ----------");
	}

//main method
	public static void main(String[] args) {
		e.file();
		e.choices();

	}

}
