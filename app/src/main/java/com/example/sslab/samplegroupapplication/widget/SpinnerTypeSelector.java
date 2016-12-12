package com.example.sslab.samplegroupapplication.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.sslab.samplegroupapplication.R;

import java.util.ArrayList;

/**
 * Created by SSLAB on 2016-09-02.
 */
public class SpinnerTypeSelector extends Spinner {
	/**
	 * 첫재 값이 기본 선택돼 있음
	 */
	public static final int TYPE_DEFAULT = 0;

	/**
	 * 처음 선택된 값 없음
	 */
	public static final int TYPE_NOT_SELECTED = 1;

	private ArrayList<String> arrayData = new ArrayList<>();

	public SpinnerTypeSelector(Context context, AttributeSet attrs ) {
		super( context, attrs );
		setBackgroundResource( R.drawable.spinner_selector );
		arrayData.clear();
	}

	public void setData( ArrayList<String> arrayData, int type ) {
		arrayData.addAll(arrayData);

		switch ( type ) {
			case TYPE_DEFAULT:
				setDataDefault( arrayData );
				break;
			case TYPE_NOT_SELECTED:
				setDataNotSelected( arrayData );
				break;
		}
	}

	public void setData( String[] data, int type ) {
		switch ( type ) {
			case TYPE_DEFAULT:
				setDataDefault( data );
				break;
			case TYPE_NOT_SELECTED:
				setDataNotSelected( data );
				break;
		}
	}

	/**
	 * @param msg 선택 없음 메시지가 따로 필요할 때. 타입 지정은 안 해도 됨
	 */
	public void setData( String[] data, String msg ) {
		setDataNotSelected( data, msg );
	}

	/**
	 * @param msg 선택 없음 메시지가 따로 필요할 때. 타입 지정은 안 해도 됨
	 */
	public void setData( ArrayList<String> array, String msg ) {
		setDataNotSelected( array, msg );
	}

	public void setDataDefault( String[] data ) {
		ArrayAdapter adapterSpinner = new ArrayAdapter( getContext(), android.R.layout.simple_spinner_item, data );
		adapterSpinner.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		setAdapter( adapterSpinner );
	}

	public void setDataNotSelected( String[] data ) {
		ArrayAdapter adapterSpinner = new ArrayAdapter( getContext(), android.R.layout.simple_spinner_item, data );
		adapterSpinner.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		SpinnerAdapter nothing = new NothingSelectedSpinnerAdapter( adapterSpinner, R.layout.text_spinner_nothing_selected, getContext() );
		setAdapter( nothing );
	}

	/**
	 * @param data
	 * @param msg  선택없음 메시지가 따로 필요할 때
	 */
	public void setDataNotSelected( String[] data, String msg ) {
		ArrayAdapter adapterSpinner = new ArrayAdapter( getContext(), android.R.layout.simple_spinner_item, data );
		adapterSpinner.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		SpinnerAdapter nothing = new NothingSelectedSpinnerAdapter( adapterSpinner, R.layout.text_spinner_nothing_selected, getContext(), msg );
		setAdapter( nothing );
	}

	/**
	 * @param array
	 * @param msg   선택없음 메시지가 따로 필요할 때
	 */
	public void setDataNotSelected( ArrayList<String> array, String msg ) {
		ArrayAdapter adapterSpinner = new ArrayAdapter( getContext(), android.R.layout.simple_spinner_item, array );
		adapterSpinner.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		SpinnerAdapter nothing = new NothingSelectedSpinnerAdapter( adapterSpinner, R.layout.text_spinner_nothing_selected, getContext(), msg );
		setAdapter( nothing );
	}

	public void setDataDefault( ArrayList<String> arrayData ) {
		ArrayAdapter adapterSpinner = new ArrayAdapter( getContext(), android.R.layout.simple_spinner_item, arrayData );
		adapterSpinner.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		setAdapter( adapterSpinner );
	}

	public void setDataNotSelected( ArrayList<String> arrayData ) {
		ArrayAdapter adapterSpinner = new ArrayAdapter( getContext(), android.R.layout.simple_spinner_item, arrayData );
		adapterSpinner.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		SpinnerAdapter nothing = new NothingSelectedSpinnerAdapter( adapterSpinner, R.layout.text_spinner_nothing_selected, getContext() );
		setAdapter( nothing );
	}

	public class NothingSelectedSpinnerAdapter implements SpinnerAdapter, ListAdapter {

		protected static final int EXTRA = 1;
		protected SpinnerAdapter adapter;
		protected Context context;
		protected int nothingSelectedLayout;
		protected int nothingSelectedDropdownLayout;
		protected LayoutInflater layoutInflater;

		private String nothingMsg;

		/**
		 * Use this constructor to have NO 'Select One...' item, instead use
		 * the standard prompt or nothing at all.
		 *
		 * @param spinnerAdapter        wrapped Adapter.
		 * @param nothingSelectedLayout layout for nothing selected, perhaps
		 *                              you want text grayed out like a prompt...
		 * @param context
		 */
		public NothingSelectedSpinnerAdapter( SpinnerAdapter spinnerAdapter, int nothingSelectedLayout, Context context ) {
			this( spinnerAdapter, nothingSelectedLayout, -1, context );
		}

		public NothingSelectedSpinnerAdapter( SpinnerAdapter spinnerAdapter, int nothingSelectedLayout, Context context, String msg ) {
			this( spinnerAdapter, nothingSelectedLayout, -1, context );
			nothingMsg = msg;
		}

		/**
		 * Use this constructor to Define your 'Select One...' layout as the first
		 * row in the returned choices.
		 * If you do this, you probably don't want a prompt on your spinner or it'll
		 * have two 'Select' rows.
		 *
		 * @param spinnerAdapter                wrapped Adapter. Should probably return false for isEnabled(0)
		 * @param nothingSelectedLayout         layout for nothing selected, perhaps you want
		 *                                      text grayed out like a prompt...
		 * @param nothingSelectedDropdownLayout layout for your 'Select an Item...' in
		 *                                      the dropdown.
		 * @param context
		 */
		public NothingSelectedSpinnerAdapter( SpinnerAdapter spinnerAdapter, int nothingSelectedLayout, int nothingSelectedDropdownLayout, Context context ) {
			this.adapter = spinnerAdapter;
			this.context = context;
			this.nothingSelectedLayout = nothingSelectedLayout;
			this.nothingSelectedDropdownLayout = nothingSelectedDropdownLayout;
			layoutInflater = LayoutInflater.from( context );
		}

		@Override
		public final View getView( int position, View convertView, ViewGroup parent ) {
			// This provides the View for the Selected Item in the Spinner, not
			// the dropdown (unless dropdownView is not set).
			if ( position == 0 ) {
				return getNothingSelectedView( parent );
			}
			return adapter.getView( position - EXTRA, null, parent ); // Could re-use
			// the convertView if possible.
		}

		/**
		 * View to show in Spinner with Nothing Selected
		 * Override this to do something dynamic... e.g. "37 Options Found"
		 *
		 * @param parent
		 * @return
		 */
		protected View getNothingSelectedView( ViewGroup parent ) {
			TextView view = ( (TextView) layoutInflater.inflate( nothingSelectedLayout, parent, false ) );
			if ( nothingMsg != null )
				view.setText( nothingMsg );
			return view;
		}

		@Override
		public View getDropDownView( int position, View convertView, ViewGroup parent ) {
			// Android BUG! http://code.google.com/p/android/issues/detail?id=17128 -
			// Spinner does not support multiple view types
			if ( position == 0 ) {
				return nothingSelectedDropdownLayout == -1 ?
						new View( context ) :
						getNothingSelectedDropdownView( parent );
			}

			// Could re-use the convertView if possible, use setTag...
			return adapter.getDropDownView( position - EXTRA, null, parent );
		}

		/**
		 * Override this to do something dynamic... For example, "Pick your favorite
		 * of these 37".
		 *
		 * @param parent
		 * @return
		 */
		protected View getNothingSelectedDropdownView( ViewGroup parent ) {
			return layoutInflater.inflate( nothingSelectedDropdownLayout, parent, false );
		}

		@Override
		public int getCount() {
			int count = adapter.getCount();
			return count == 0 ? 0 : count + EXTRA;
		}

		@Override
		public Object getItem( int position ) {
			return position == 0 ? null : adapter.getItem( position - EXTRA );
		}

		@Override
		public int getItemViewType( int position ) {
			return 0;
		}

		@Override
		public int getViewTypeCount() {
			return 1;
		}

		@Override
		public long getItemId( int position ) {
			return position >= EXTRA ? adapter.getItemId( position - EXTRA ) : position - EXTRA;
		}

		@Override
		public boolean hasStableIds() {
			return adapter.hasStableIds();
		}

		@Override
		public boolean isEmpty() {
			return adapter.isEmpty();
		}

		@Override
		public void registerDataSetObserver( DataSetObserver observer ) {
			adapter.registerDataSetObserver( observer );
		}

		@Override
		public void unregisterDataSetObserver( DataSetObserver observer ) {
			adapter.unregisterDataSetObserver( observer );
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEnabled( int position ) {
			return position != 0; // Don't allow the 'nothing selected'
			// item to be picked.
		}

	}
}
