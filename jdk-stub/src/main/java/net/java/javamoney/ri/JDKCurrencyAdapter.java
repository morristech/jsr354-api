package net.java.javamoney.ri;


import java.io.Serializable;

import javamoney.util.Currency;

import javax.money.CurrencyUnit;

/**
 * Adapter that implements the new {@link CurrencyUnit} interface using the
 * JDK's {@link Currency}.
 * 
 * @author Anatole Tresch
 */
public final class JDKCurrencyAdapter implements CurrencyUnit, Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -2523936311372374236L;

	/**
	 * ISO 4217 currency code for this currency.
	 * 
	 * @serial
	 */
	private Currency currency;

	/**
	 * Private constructor.
	 * 
	 * @param currency
	 */
	private JDKCurrencyAdapter(Currency currency) {
		if (currency == null) {
			throw new IllegalArgumentException("Currency required.");
		}
		this.currency = currency;
	}

	public static CurrencyUnit valueOf(Currency currency) {
		// TODO implement caching!
		return new JDKCurrencyAdapter(currency);
	}

	@Override
	public boolean isVirtualCurrency() {
		return false;
	}

	/**
	 * Get the namepsace of this {@link CurrencyUnit}, returns 'ISO-4217'.
	 */
	@Override
	public String getNamespace() {
		return "ISO-4217";
	}

	@Override
	public long getValidFrom() {
		return -1;
	}

	@Override
	public long getValidUntil() {
		return -1;
	}

	@Override
	public int compareTo(CurrencyUnit currency) {
		// TODO Auto-generated method stub
		int compare = getNamespace().compareTo(currency.getNamespace());
		if (compare == 0) {
			compare = getCurrencyCode().compareTo(currency.getCurrencyCode());
		}
		// TODO check for validFrom, until
		return compare;
	}

	@Override
	public String getCurrencyCode() {
		return this.currency.getCurrencyCode();
	}

	@Override
	public int getNumericCode() {
		return this.currency.getNumericCode();
	}

	@Override
	public int getDefaultFractionDigits() {
		return this.currency.getDefaultFractionDigits();
	}
}
