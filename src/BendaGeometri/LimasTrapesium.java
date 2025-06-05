	package BendaGeometri;

	import java.util.InputMismatchException;
	import java.util.Scanner;

	public class LimasTrapesium extends Trapesium {
		private double tinggiLimas;
		private double luasSegitigaAtas;
		private double luasSegitigaBawah;
		private double luasSegitigaKiri;
		private double luasSegitigaKanan;
		private double selisihBasis;
		private double tinggiSisiMiring;
		private double luasAlas;
		private double volume;
		private double luasPermukaan;

		public LimasTrapesium(double alasAtas, double alasBawah, double tinggiTrapesium, double sisiKiri, double sisiKanan,
				double tinggiLimas) {
			super(alasAtas, alasBawah, tinggiTrapesium, sisiKiri, sisiKanan);
			this.tinggiLimas = tinggiLimas;
		}

		public double menghitungVolume() {
			luasAlas = menghitungLuas();
			volume = (1 / 3.0) * luasAlas * tinggiLimas;
			return volume;
		}

		public double menghitungVolume(double alasAtasBaru, double alasBawahBaru, double tinggiTrapesiumBaru,
				double tinggiLimasBaru) {
			luasAlas = menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru);
			volume = (1 / 3.0) * luasAlas * tinggiLimasBaru;
			return volume;
		}

		public double menghitungLuasPermukaan() {
			luasAlas = super.menghitungLuas();
			luasSegitigaAtas = 0.5 * super.alasAtas * tinggiLimas;
			luasSegitigaBawah = 0.5 * super.alasBawah * tinggiLimas;
			selisihBasis = (super.alasBawah - super.alasAtas) / 2.0;
			tinggiSisiMiring = Math.sqrt(tinggiLimas * tinggiLimas + selisihBasis * selisihBasis);
			luasSegitigaKiri = 0.5 * super.sisiMiringKiri * tinggiSisiMiring;
			luasSegitigaKanan = 0.5 * super.sisiMiringKanan * tinggiSisiMiring;
			luasPermukaan = luasAlas + luasSegitigaAtas + luasSegitigaBawah + luasSegitigaKiri + luasSegitigaKanan;
			return luasPermukaan;
		}

		public double menghitungLuasPermukaan(double alasAtasBaru, double alasBawahBaru, double tinggiTrapesiumBaru,
				double tinggiLimasBaru) {
			luasAlas = menghitungLuas(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru);
			luasSegitigaAtas = 0.5 * alasAtasBaru * tinggiLimasBaru;
			luasSegitigaBawah = 0.5 * alasBawahBaru * tinggiLimasBaru;
			selisihBasis = (alasBawahBaru - alasAtasBaru) / 2.0;
			tinggiSisiMiring = Math.sqrt(tinggiLimasBaru * tinggiLimasBaru + selisihBasis * selisihBasis);
			luasSegitigaKiri = 0.5 * sisiMiringKiri * tinggiSisiMiring;
			luasSegitigaKanan = 0.5 * sisiMiringKanan * tinggiSisiMiring;
			luasPermukaan = luasAlas + luasSegitigaAtas + luasSegitigaBawah + luasSegitigaKiri + luasSegitigaKanan;
			return luasPermukaan;
		}

		@Override
		public String getNamaBenda() {
			return "Limas Trapesium";
		}

		public void prosesInputDataUlang() {
			Scanner inputData = new Scanner(System.in);
			while (true) {
				System.out.print("\nIngin mengubah alas atas, alas bawah, tinggi trapesium, dan tinggi limas? (Y/N): ");
				String jawaban = inputData.nextLine();
				if (jawaban.equalsIgnoreCase("Y")) {
					while (true) {
						try {
							System.out.print("Masukkan alas atas baru: ");
							double alasAtasBaru = inputData.nextDouble();

							System.out.print("Masukkan alas bawah baru: ");
							double alasBawahBaru = inputData.nextDouble();

							System.out.print("Masukkan tinggi trapesium baru: ");
							double tinggiTrapesiumBaru = inputData.nextDouble();

							System.out.print("Masukkan tinggi limas baru: ");
							double tinggiLimasBaru = inputData.nextDouble();
							inputData.nextLine();

							if (alasAtasBaru <= 0 || alasBawahBaru <= 0 || tinggiTrapesiumBaru <= 0 || tinggiLimasBaru <= 0) {
								System.out.println("Semua nilai harus lebih dari 0.");
								continue;
							}

							this.volume = menghitungVolume(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru,
									tinggiLimasBaru);
							this.luasPermukaan = menghitungLuasPermukaan(alasAtasBaru, alasBawahBaru, tinggiTrapesiumBaru,
									tinggiLimasBaru);

							System.out.printf("\nVolume Limas Trapesium: %.2f\n", volume);
							System.out.printf("Luas Permukaan Limas Trapesium: %.2f\n", luasPermukaan);
							break;
						} catch (InputMismatchException e) {
							System.out.println("Input harus berupa angka.");
							inputData.nextLine();
						}
					}
					break;
				} else if (jawaban.equalsIgnoreCase("N")) {
					break;
				} else {
					System.out.println("Jawaban harus Y atau N.");
				}
			}
		}
	}