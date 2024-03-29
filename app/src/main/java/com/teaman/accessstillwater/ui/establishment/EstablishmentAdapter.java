package com.teaman.accessstillwater.ui.establishment;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.teaman.accessstillwater.R;
import com.teaman.accessstillwater.base.BaseRecyclerAdapter;
import com.teaman.accessstillwater.base.ItemCallback;
import com.teaman.data.entities.Establishment;

/**
 * Created by weava on 3/14/16.
 */
public class EstablishmentAdapter extends BaseRecyclerAdapter<Establishment, EstablishmentViewHolder> {

    private ItemCallback<Establishment> mEstablishmentItemCallback;
    private Context mContext;

    public EstablishmentAdapter(ItemCallback<Establishment> establishmentItemCallback, Context context) {
        this.mEstablishmentItemCallback = establishmentItemCallback;
        mContext = context;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item_establishment;
    }

    @Override
    public EstablishmentViewHolder inflateViewHolder(View v) {
        return new EstablishmentViewHolder(v, mEstablishmentItemCallback, mContext);
    }

    @Override
    public void onBindViewHolder(EstablishmentViewHolder holder, int position) {
        Log.d("Binding View Name", mElements.get(position).getPlacesId());
        holder.bind(mElements.get(position));
    }
}
