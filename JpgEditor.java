import java.util.*;
import java.io.File;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Color;
import java.awt.*;

class JpgEditor
{
    public static void main()
    {
        System.out.println("Store the images in the path: C:user/desktop/ImageEditor/ImageEditor");
        System.out.println("What is the name of your image");
        Scanner in = new Scanner(System.in);
        String n = in.nextLine();
        String Name = "";
        for(int i = 0; i<n.length(); i++)
        {
            if(n.charAt(i) == '.')
            {
                break;
            }
            
            else
            {
                Name = Name + n.charAt(i);
            }
        }
        //String imageName = Name +".jpg";
        
        File inputFile = new File(n);
        
        try
        {
             BufferedImage inputImage = ImageIO.read(inputFile);
             //printPixelValues(inputImage);
             
             
             while(true)
             {
                 
             
             printMenu();
              
              int ch = in.nextInt();
              
              switch(ch)
              {
                  case 1:
                      
                      BufferedImage grayScale = convertToGrayScale(inputImage);
                      File grayScaleImage = new File(Name+"-grayscale.jpg");
                      ImageIO.write(grayScale, "jpg", grayScaleImage );
                      break;
                      
                      
                  case 2:
                      
                      BufferedImage rotate = rotate(inputImage);
                      File r = new File(Name+"-rotate-Anticlockwise.jpg");
                      ImageIO.write(rotate, "jpg", r);
                      break;
                      
                   case 3:
                       
                       BufferedImage rotate_anti = rotate(inputImage);
                       rotate_anti = InverseHorizontal(rotate_anti);
                       File r_anti = new File(Name+"-rotate-clockwise.jpg");
                       ImageIO.write(rotate_anti, "jpg", r_anti);
                       break;
                       
                   case 4:
                       
                       BufferedImage changedBrightness = changeBrightness(inputImage);
                       File changedBrightnessImage = new File(Name+"-changedBrightnessImage.jpg");
                       ImageIO.write(changedBrightness, "jpg", changedBrightnessImage);
                       break;
                       
                   case 5:
                       
                       BufferedImage mirrorImage = InverseHorizontal(inputImage);
                       File mirrorImg = new File(Name+"-InverseHorizontal.jpg");
                       ImageIO.write(mirrorImage, "jpg", mirrorImg);
                       
                       
                       break;
                       
                    
                   case 6:
                       
                       BufferedImage IverseVertical = InverseHorizontal(inputImage);
                       IverseVertical = InverseVertical(IverseVertical);
                       File InverseV = new File(Name+"-InverseVertical.jpg");
                       ImageIO.write(IverseVertical, "jpg", InverseV);
                       break;
                       
                   case 7:
                       
                       BufferedImage blurImage = blur(inputImage);
                       File blur = new File(Name+"-blur.jpg");
                       ImageIO.write(blurImage, "jpg", blur);
                       break;
                       
                
                   case 8:
                       
                       
                       BufferedImage edgeImage = convertToGrayScale(inputImage);
                       edgeImage = edgeDetection(edgeImage);
                       File EI = new File(Name+"-edgeDetected.jpg");
                       ImageIO.write(edgeImage, "jpg", EI );
                       break;
                       
                   case 9:
                       
                       BufferedImage invertColorImage = colorInversion(inputImage);
                       
                       File E = new File(Name+"-negative.jpg");
                       ImageIO.write(invertColorImage, "jpg", E );
                       break;
                       
                   case 10:
                       
                       BufferedImage hulkImage = hulkScale(inputImage);
                       
                       File h = new File(Name+"-hulkScale.jpg");
                       ImageIO.write(hulkImage, "jpg", h );
                       break;
                       
                       
                   case 11:
                       
                         BufferedImage nolanImage = NolanEffect(inputImage);
                       
                       File nolan = new File(Name+"-nolanEffect.jpg");
                       ImageIO.write(nolanImage, "jpg", nolan );
                       break;
                       
                   case 12:
                       System.out.println("Program got exit");
                       System.exit(0);
                       
                       break;
                       
                   default:
                       System.out.println("Invalid input: ");
                       
                       
                       
                       
                                                                                                      
                                        
              }
              System.out.println("Image has been created and saved");
              
            }
             
             
             
             
             
             
             
             
             
             
             /*BufferedImage rotate = rotate(inputImage);
             File r = new File(Name+"-rotate.jpg");
             ImageIO.write(rotate, "jpg", r);*/
             
             
             
        }
        
        catch(IOException e)
        {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    public static void printMenu()
    {
        System.out.println("1. Convert to GrayScale");
        System.out.println("2. Rotate 90 degrees Anti-clockwise");
        System.out.println("3. Rotate 90 degrees Clockwise");
        System.out.println("4. Adjust Brightness");
        System.out.println("5. Invert the Image Horizontaly");
        System.out.println("6. Invert the Image Verticaly");
        System.out.println("7. Blur the Image");
        System.out.println("8. Edge Detection");
        System.out.println("9. Invert Colors");
        System.out.println("10. Hulk Scale");
        System.out.println("11. Christopher Nolan Effect");
        System.out.println("12. Exit.");
        
        
    }
    
    
    public static BufferedImage convertToGrayScale(BufferedImage inputImage)
    {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for(int i = 0 ; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                outputImage.setRGB(j, i, inputImage.getRGB(j,i));
                
            }
        }
        
        return outputImage;
    }
    
    public static BufferedImage changeBrightness(BufferedImage inputImage)
    {
        System.out.println("Enter the amount of brightness to be increased on a scale from 0 to 100");
        Scanner in = new Scanner(System.in);
        int increase = in.nextInt();
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                Color pixels = new Color(inputImage.getRGB(j, i));
                int red = pixels.getRed();
                int blue = pixels.getBlue();
                int green = pixels.getGreen();
                
                red = red + (increase*red/100);
                blue = blue +(increase*blue/100);
                green = green + (increase*green/100);
                if(red>255) red = 255; if(red<0) red=0;
                if(blue>255) blue = 255; if(blue<0) blue=0;
                if(green>255) green = 255; if(green<0) green=0;
                
                Color newPixel = new Color(red, green, blue);
                outputImage.setRGB(j, i, newPixel.getRGB());
                
            }
        }
        
        
                
        return outputImage;
    }
    
    
    public static BufferedImage edgeDetection(BufferedImage inputImage)
    {
        System.out.println("Enter the amount of strokes 0 to 100");
        Scanner in = new Scanner(System.in);
        int strokes = in.nextInt();
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<height-strokes; i++)
        {
            for(int j = 0; j<width-strokes; j++)
            {
                Color pixels = new Color(inputImage.getRGB(j, i));
                Color u = new Color(inputImage.getRGB(j+strokes, i+strokes));
                int red = pixels.getRed();
                int blue = pixels.getBlue();
                int green = pixels.getGreen();
                
                int URed = u.getRed();
                int UBlue = u.getBlue();
                int UGreen = u.getGreen();
                
                URed = URed - red;
                UBlue = UBlue - blue;
                UGreen = UGreen - green;
                
                
                
                
                if(URed>255) URed = 255; if(URed<0) URed=0;
                if(UBlue>255) UBlue = 255; if(UBlue<0) UBlue=0;
                if(UGreen>255) UGreen = 255; if(UGreen<0) UGreen=0;
                
                Color newPixel = new Color(URed,UGreen, UBlue);
                outputImage.setRGB(j, i, newPixel.getRGB());
                
            }
        }
        
        
                
        return outputImage;
    }
    
    
    public static BufferedImage colorInversion(BufferedImage inputImage)
    {
        
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                Color pixels = new Color(inputImage.getRGB(j, i));
                
                int red = pixels.getRed();
                int blue = pixels.getBlue();
                int green = pixels.getGreen();
                
                
                int URed = 255 - red;
                int UBlue = 255 - blue;
                int UGreen = 255 - green;
                
                
                
                
                if(URed>255) URed = 255; if(URed<0) URed=0;
                if(UBlue>255) UBlue = 255; if(UBlue<0) UBlue=0;
                if(UGreen>255) UGreen = 255; if(UGreen<0) UGreen=0;
                
                Color newPixel = new Color(URed,UGreen, UBlue);
                outputImage.setRGB(j, i, newPixel.getRGB());
                
            }
        }
        
        
                
        return outputImage;
    }
    
    
    public static BufferedImage hulkScale(BufferedImage inputImage)
    {
        
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                Color pixels = new Color(inputImage.getRGB(j, i));
                
                int red = pixels.getRed();
                int blue = pixels.getBlue();
                int green = pixels.getGreen();
                
                
                
                
                
                
                
                
                Color newPixel = new Color(0,green,0 );
                outputImage.setRGB(j, i, newPixel.getRGB());
                
            }
        }
        
        
                
        return outputImage;
    }
    
    
    public static BufferedImage NolanEffect(BufferedImage inputImage)
    {
        
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                Color pixels = new Color(inputImage.getRGB(j, i));
                
                int red = pixels.getRed();
                int blue = pixels.getBlue();
                int green = pixels.getGreen();
                
                
                
                
                
                
                
                
                Color newPixel = new Color(red,green,0 );
                outputImage.setRGB(j, i, newPixel.getRGB());
                
            }
        }
        
        
                
        return outputImage;
    }
    
    
    
    
     
    
    public static BufferedImage InverseVertical(BufferedImage inputImage)
    {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        
        
        
        int k = height-1;
        
        for(int i = 0; i<height; i++)
        {
            
            for(int j = 0; j<width; j++)
            {
                if(k>=0)
                {
                    outputImage.setRGB(j, i, inputImage.getRGB(j, k));
                    
                }
                
            }
            k--;
        }
        
        return outputImage;
    }
    
    public static BufferedImage InverseHorizontal(BufferedImage inputImage)
    {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        
        for(int i = 0; i<height; i++)
        {
            int k = width-1;
            for(int j = 0; j<width; j++)
            {
                if(k>=0)
                {
                    outputImage.setRGB(k, i, inputImage.getRGB(j, i));
                    k--;
                }
                
            }
        }
        
                
        return outputImage;
    }
    
    public static BufferedImage rotate(BufferedImage inputImage)
    {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        BufferedImage outputImage = new BufferedImage(height, width, BufferedImage.TYPE_3BYTE_BGR);
        int i1 = 0;
        
        int i = 0; 
        while(i<height && i1<height)
        {
            int j =0; int j1=0;
            while(j<width && j1<width )
            {
                outputImage.setRGB(i1, j1, inputImage.getRGB(j, i));
                j++; j1++;
            }
            i++; i1++;
        }
        
        return outputImage;
    }
    
    public static BufferedImage blur(BufferedImage inputImage)
    {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();  
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the amount of blurness from 0 to 50: ");
        int blurness = in.nextInt();
        
        System.out.println("Wait for "+blurness+" seconds.");
        
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        
        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                
                outputImage.setRGB(j, i, inputImage.getRGB(j, i));
            }
        }
        
        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                
                outputImage.setRGB(j, i, average(outputImage, i, j, blurness));
            }
        }
        
        return outputImage;
        
        
    }
    
    public static int average(BufferedImage outImage, int x, int y, int n)
    {
        int red=0, green=0, blue=0, pixels=0;
    
        int height = outImage.getHeight();
        int width = outImage.getWidth();
        if( (x+n)<height && (y+n)<width)
        {
            for(int i = x; i<n+x; i++)
          {
               for(int j = y; j<n+y; j++)
              {
                  Color pixel = new Color(outImage.getRGB(j, i));
                  red += pixel.getRed();
                  blue += pixel.getBlue();
                  green += pixel.getGreen();
                  
              }
           }
           
           red = red/(n*n);
           blue = blue/(n*n);
           green = green/(n*n);
           Color newPixel = new Color(red, green, blue);
           pixels = newPixel.getRGB();
        }
        
        else if( (x+n)>=height && (y+n)<width)
        {
              for(int i = x; i>x-n; i--)
          {
               for(int j = y; j<n+y; j++)
              {
                  Color pixel = new Color(outImage.getRGB(j, i));
                  red += pixel.getRed();
                  blue += pixel.getBlue();
                  green += pixel.getGreen();
              }
           }
           
           red = red/(n*n);
           blue = blue/(n*n);
           green = green/(n*n);
           Color newPixel = new Color(red, green, blue);
           pixels = newPixel.getRGB();
        }
        
        else if( (x+n)<height && (y+n)>=width)
        {
              for(int i = x; i>x+n; i++)
          {
               for(int j = y; j>y-n; j--)
              {
                  Color pixel = new Color(outImage.getRGB(j, i));
                  red += pixel.getRed();
                  blue += pixel.getBlue();
                  green += pixel.getGreen();
              }
           }
           
           red = red/(n*n);
           blue = blue/(n*n);
           green = green/(n*n);
           Color newPixel = new Color(red, green, blue);
           pixels = newPixel.getRGB();
        }
        
        else if( (x+n)>=height && (y+n)>=width)
        {
              for(int i = x; i>x-n; i--)
          {
               for(int j = y; j>y-n; j--)
              {
                  Color pixel = new Color(outImage.getRGB(j, i));
                  red += pixel.getRed();
                  blue += pixel.getBlue();
                  green += pixel.getGreen();
              }
           }
           
           red = red/(n*n);
           blue = blue/(n*n);
           green = green/(n*n);
           Color newPixel = new Color(red, green, blue);
           pixels = newPixel.getRGB();
        }
        
        
        
        
        
         
         
         
         return pixels;
        
        
    }
    
    
    
    
    
    public static void printPixelValues(BufferedImage inputImage)
    {
        int height = inputImage.getHeight();
        int width = inputImage.getWidth();
        System.out.println("Height of the image is: 822 : " +height);
        System.out.println("Width of the image is: 540: "+ width);
        for(int i = 0; i<height; i++)
        {
            for(int j = 0; j<width; j++)
            {
                //System.out.print(inputImage.getRGB(j, i) );
                
                Color pixel = new Color(inputImage.getRGB(j, i));
                int red = pixel.getRed(); 
                int green = pixel.getGreen(); 
                int blue = pixel.getBlue();
                
                System.out.print(red +" "+blue+" "+green+"\t");
            }
            System.out.println();
        }
    }
    
    
    
}