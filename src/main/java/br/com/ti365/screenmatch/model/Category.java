package br.com.ti365.screenmatch.model;

public enum Category {
	ACAO("Action"), ROMANCE("Romance"), COMEDIA("Comedy"), CRIME("Crime"), DRAMA("Drama");

	private String categoryOmdb;

	private Category(String categoryOmdb) {
		this.categoryOmdb = categoryOmdb;
	}

	public static Category fromString(String text) {
		for (Category category : Category.values()) {
			if (category.categoryOmdb.equalsIgnoreCase(text)) {
				return category;
			}
		}
		throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
	}

}