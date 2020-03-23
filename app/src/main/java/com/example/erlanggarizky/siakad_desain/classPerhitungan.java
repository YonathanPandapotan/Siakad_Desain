package com.example.erlanggarizky.siakad_desain;

public class classPerhitungan {
    float[] ipsMahasiswa = new float[8];
    int ipkMahasiswa;

    classPerhitungan(){

    }

    public double[] hitungIPS(double[] ipsMahasiswa, double ipk){
        int i=0;
        int[] semester = new int[8];


        /*Menentukan nilai total IPS yang diinginkan user dan mendapatkan nilai total IPS sementara yang dia miliki*/
        double nilaiTotal = ipk*8;
        double nilaiTotalSementara = jumlahIpsSementara(ipsMahasiswa);
        double nilaiKurang = nilaiTotal - nilaiTotalSementara;

        /*Menentukan semester mana saja yang masih kosong*/
        for(int j=0;j<ipsMahasiswa.length;j++){
            if(ipsMahasiswa[j]==0.0){
                semester[i] = j;
                i++;
            }
        }
        int[] semesterInput = new int[i];

        for(int l=0;l<i;l++){
            semesterInput[l] = semester[l];
        }

        /*Menentukan nilai IPS yang harus didapat per semester berikutnya*/
        double minimalIps = nilaiKurang/semesterInput.length;

        for(int k=0;k<semesterInput.length;k++){
            ipsMahasiswa[semesterInput[k]] = minimalIps;
        }

        return ipsMahasiswa;
    }

    private double jumlahIpsSementara(double[] ipsMahasiswa){
        double sum = 0;
        for (int i=0;i<ipsMahasiswa.length;i++){
            sum = sum + ipsMahasiswa[i];
        }
        return sum;
    }
}
