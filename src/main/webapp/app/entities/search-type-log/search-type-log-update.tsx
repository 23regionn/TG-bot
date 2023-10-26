import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './search-type-log.reducer';
import { ISearchTypeLog } from 'app/shared/model/search-type-log.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISearchTypeLogUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SearchTypeLogUpdate = (props: ISearchTypeLogUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { searchTypeLogEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/search-type-log');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateLog = convertDateTimeToServer(values.dateLog);

    if (errors.length === 0) {
      const entity = {
        ...searchTypeLogEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="gidApp.searchTypeLog.home.createOrEditLabel" data-cy="SearchTypeLogCreateUpdateHeading">
            Create or edit a SearchTypeLog
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : searchTypeLogEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="search-type-log-id">ID</Label>
                  <AvInput id="search-type-log-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="chatIdLabel" for="search-type-log-chatId">
                  Chat Id
                </Label>
                <AvField id="search-type-log-chatId" data-cy="chatId" type="string" className="form-control" name="chatId" />
              </AvGroup>
              <AvGroup>
                <Label id="dateLogLabel" for="search-type-log-dateLog">
                  Date Log
                </Label>
                <AvInput
                  id="search-type-log-dateLog"
                  data-cy="dateLog"
                  type="datetime-local"
                  className="form-control"
                  name="dateLog"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.searchTypeLogEntity.dateLog)}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="inlineSearchLabel">
                  <AvInput
                    id="search-type-log-inlineSearch"
                    data-cy="inlineSearch"
                    type="checkbox"
                    className="form-check-input"
                    name="inlineSearch"
                  />
                  Inline Search
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="pageSearchLabel">
                  <AvInput
                    id="search-type-log-pageSearch"
                    data-cy="pageSearch"
                    type="checkbox"
                    className="form-check-input"
                    name="pageSearch"
                  />
                  Page Search
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="pageNumberLabel" for="search-type-log-pageNumber">
                  Page Number
                </Label>
                <AvField id="search-type-log-pageNumber" data-cy="pageNumber" type="string" className="form-control" name="pageNumber" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/search-type-log" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  searchTypeLogEntity: storeState.searchTypeLog.entity,
  loading: storeState.searchTypeLog.loading,
  updating: storeState.searchTypeLog.updating,
  updateSuccess: storeState.searchTypeLog.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SearchTypeLogUpdate);
