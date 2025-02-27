import java.util.Scanner;

class WoodenBlinds {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking user input
        System.out.print("Enter strip size in inches: ");
        int stripSize = scanner.nextInt();

        System.out.print("Enter number of strips needed: ");
        int numStrips = scanner.nextInt();

        // Available wooden piece sizes in inches
        int[] woodSizes = {48, 60, 72};

        int bestSize = 0;
        int minWastage = Integer.MAX_VALUE;
        int totalWoodPieces = 0;
        int bestWastePerPiece = 0;

        for (int woodSize : woodSizes) {
            if (woodSize < stripSize) continue; // Skip invalid wood sizes

            int stripsPerPiece = woodSize / stripSize;
            if (stripsPerPiece == 0) continue; // Avoid division by zero

            int requiredPieces = numStrips / stripsPerPiece;
            int remainingStrips = numStrips % stripsPerPiece;
            
            if (remainingStrips > 0) {
                requiredPieces += 1; // One more piece needed for remaining strips
            }
            
            int wastePerPiece = woodSize % stripSize;
            int totalWastage = (requiredPieces - 1) * wastePerPiece;

            // If an extra piece is required for remaining strips, compute extra wastage
            if (remainingStrips > 0) {
                totalWastage += woodSize - (remainingStrips * stripSize);
            }

            // Check for minimum wastage
            if (totalWastage < minWastage) {
                minWastage = totalWastage;
                bestSize = woodSize;
                totalWoodPieces = requiredPieces;
                bestWastePerPiece = wastePerPiece;
            }
        }

        // Display the result
        if (bestSize == 0) {
            System.out.println("No suitable wood size available.");
        } else {
            System.out.println("Best wooden piece size: " + bestSize + " inches");
            System.out.println("Total wooden pieces required: " + totalWoodPieces);
            System.out.println("Wastage per piece: " + bestWastePerPiece + " inches");
            System.out.println("Total wastage: " + minWastage + " inches");
        }

        scanner.close();
    }
}
