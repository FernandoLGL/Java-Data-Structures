public void ordenar() {
		for (int z = 0; z < 15; z++) {
			int aux;
			if (repositorio[z] > repositorio[z + 1]) {
				if (repositorio[z + 1] == 0) {
				} else {
					aux = repositorio[z];
					repositorio[z] = repositorio[z + 1];
					repositorio[z + 1] = aux;
					for (int i = z; i != 0; i--) {
						if (repositorio[z - i] > repositorio[z]) {
							aux = repositorio[z - i];
							repositorio[z - i] = repositorio[z];
							repositorio[z] = aux;
						}

					}

				}
			}

		}
		for (int x = 0; x < 10; x++) {
			System.out.println(repositorio[x]);
		}
	}
