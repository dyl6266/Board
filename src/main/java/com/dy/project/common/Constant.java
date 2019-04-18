package com.dy.project.common;

public class Constant {

	public enum YesNo {
		Y, N;
	}

	public enum Result {
		OK(1), FAIL(0);

		private int result;

		private Result(int result) {
			this.result = result;
		}

		public int getResult() {
			return result;
		}
	}

	public enum TableName {
		TB_BOARD("board");

		private String tableName;

		private TableName(String tableName) {
			this.tableName = tableName;
		}

		public String getTableName() {
			return tableName;
		}
	}

}
