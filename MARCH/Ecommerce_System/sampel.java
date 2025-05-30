
// while (true) {
//     System.out.println("\n1. Register User\n2. Login\n3. Send Shipment\n4. Exit");
//     int choice = sc.nextInt();
//     sc.nextLine();

//     switch (choice) {
//         case 1:
//             System.out.print("Enter Name: ");
//             String name = sc.nextLine();
//             System.out.print("Enter Address: ");
//             String address = sc.nextLine();
//             System.out.print("Enter Password: ");
//             String password = sc.nextLine();
//             System.out.print("Enter Adhaar ID: ");
//             String adhaarID = sc.nextLine();
//             System.out.print("Enter Contact No: ");
//             long contactNo = sc.nextLong();
//             manager.register(name, address, password, adhaarID, contactNo, false);
//             break;

//         case 2:
//             System.out.print("Enter ID: ");
//             String id = sc.nextLine();
//             System.out.print("Enter Password: ");
//             String pass = sc.nextLine();
//             manager.login(id, pass);
//             break;

//         case 3:
//             System.out.print("Enter Contractor ID: ");
//             String contractorID = sc.nextLine();
//             System.out.print("Enter Admin ID: ");
//             String adminID = sc.nextLine();
//             System.out.print("Enter Product ID: ");
//             String productID = sc.nextLine();
//             System.out.print("Enter Product Name: ");
//             String productName = sc.nextLine();
//             System.out.print("Enter Product Purpose: ");
//             String productPurpose = sc.nextLine();
//             System.out.print("Enter Product Genre: ");
//             String productGenre = sc.nextLine();
//             System.out.print("Enter Product Cost: ");
//             double productCost = sc.nextDouble();
//             System.out.print("Enter Quantity: ");
//             int quantity = sc.nextInt();

//             Product product = new Product(productID, productName, productPurpose, productGenre, productCost);
//             Ship_Product shipment = new Ship_Product(contractorID, adminID, product, quantity);

//             boolean found = false;
//             for (Contractor contractor : manager.Contractors) {
//                 if (contractor.ID.equals(contractorID)) {
//                     contractor.sendProducts(shipment);
//                     found = true;
//                     break;
//                 }
//             }
//             if (!found) {
//                 System.out.println("Contractor not found.");
//             }
//             break;

//         case 4:
//             System.out.println("Exiting... Goodbye!");
//             return;

//         default:
//             System.out.println("Invalid choice. Try again.");
//     }
// }