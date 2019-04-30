package com.dy.project.common;

public class Constant {

	public enum YesNo {
		Y, N;
	}

	public enum Result {
		OK(1, true), FAIL(0, false);

		private int firstValue;
		private boolean secondValue;

		private Result(int firstValue, boolean secondValue) {
			this.firstValue = firstValue;
			this.secondValue = secondValue;
		}

		public int getFirstValue() {
			return firstValue;
		}

		public boolean getsecondValue() {
			return secondValue;
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
